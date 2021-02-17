package com.example.involtaday1.ui.notification

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.involtaday1.R
import com.example.involtaday1.ui.view_pager.ViewPagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.android.synthetic.main.fragment_notify.*
import kotlinx.android.synthetic.main.fragment_view_pager.*
import org.jetbrains.anko.support.v4.toast
import java.text.SimpleDateFormat
import java.util.*


class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_notification.setOnClickListener(this::handleClick)
        btn_snackbar.setOnClickListener(this::handleClick)
        btn_toast.setOnClickListener(this::handleClick)
        btn_dialog.setOnClickListener(this::handleClick)
    }

    private fun handleClick(view: View) {
        if (view is Button) {
            val msg = getShownMessage(view)

            when (view.id) {
                R.id.btn_notification -> showNotification(msg)
                R.id.btn_toast -> toast(msg)
                R.id.btn_dialog -> showDialog(msg)
                R.id.btn_snackbar ->   Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun getShownMessage(button: Button): String {
        val c = Calendar.getInstance()
        val month = c.get(Calendar.MONTH)+1
        val day = c.get(Calendar.DAY_OF_MONTH)

        val buttonText = button.text

        return getString(R.string.notify_shown_text, "$month месяц по счёту", "$day число", buttonText)
    }

    private fun showDialog(msg: String) {
        AlertDialog.Builder(context).setMessage(msg).create().show()
    }

    private fun showNotification(msg: String) {
        NotificationHelper.showNotificationWithText(msg, requireContext())
    }
}