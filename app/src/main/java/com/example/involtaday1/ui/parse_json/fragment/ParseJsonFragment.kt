package com.example.involtaday1.ui.parse_json.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.involtaday1.R
import com.example.involtaday1.ui.parse_json.SimpleFileWriter
import org.json.JSONArray
import org.json.JSONObject
import com.example.involtaday1.ui.parse_json.model.RadioStationModel
import com.example.involtaday1.ui.parse_json.adapter.RadioStationsListAdapter

class ParseJsonFragment : Fragment() {

    companion object {
        private const val jsonFileName = "parse_radio.json"
    }

    private val adapter = RadioStationsListAdapter()

    private val simpleFileWriter: SimpleFileWriter by lazy {
        SimpleFileWriter(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_parse_json, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            val rvRadioList: RecyclerView = findViewById(R.id.radio_recycler_view)
            val bConvert: Button = findViewById(R.id.convert_btn)

            rvRadioList.adapter = adapter
            adapter.initList(loadRadioList())

            bConvert.setOnClickListener {
                val selectedList = adapter.getSelectedList()
                if (selectedList.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Choose",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    convertAndSaveSelectedList(selectedList)
                }
            }
        }
    }

    private fun convertAndSaveSelectedList(selectedList: List<RadioStationModel>) {
        val jsonString = convertListToJson(selectedList).toString()
        saveFile(jsonString)
    }

    private fun convertListToJson(list: List<RadioStationModel>): JSONArray {
        val jsonArray = JSONArray()
        list.forEach {
            val jObj = JSONObject()
            jObj.put("stream", it.isSelected)
            jObj.put("name", it.name)
            jObj.put("image", it.imageUrl)

            jsonArray.put(jObj)
        }
        return jsonArray
    }

    private fun showToastShort(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun saveFile(jsonString: String) {
        if (checkWriteExternalStoragePermission()) {
            val isSuccess = simpleFileWriter.writeFileToExternalCache(
                "ExampleApplication",
                jsonFileName,
                jsonString
            )
            if (isSuccess) {
                adapter.clearSelection()
                showToastShort(
                    requireContext(),
                    getString(R.string.parse_json_file_is_created)
                )
            } else {
                showToastShort(
                    requireContext(),
                    getString(R.string.parse_json_file_fail)
                )
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            when (requestCode) {
                1 -> {
                    convertAndSaveSelectedList(adapter.getSelectedList())
                }
            }
        }
    }

    private fun checkWriteExternalStoragePermission(): Boolean {
        if ((ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED)
        ) return true
        else requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        return false
    }

    private fun loadRadioList(): List<RadioStationModel> {
        val jsonText = getJsonText()
        val jOnj = JSONObject(jsonText)

        return convertJsonToRadioList(jOnj)
    }

    private fun getJsonText(): String {
        val rawStream = resources.openRawResource(R.raw.radio)
        val streamReader = rawStream.reader()
        return streamReader.readText()
    }

    private fun convertJsonToRadioList(jsonObject: JSONObject): List<RadioStationModel> {
        val list = mutableListOf<RadioStationModel>()
        val stationsArray = jsonObject.getJSONArray("stations")
        for (i in 0 until stationsArray.length()) {
            val obj = JSONObject(stationsArray.get(i).toString())
            val name = obj.getString("name")
            val stream = obj.getString("stream")
            val image = obj.getString("image")

            val item = RadioStationModel(name, stream, image)
            list.add(item)
        }

        return list
    }
}