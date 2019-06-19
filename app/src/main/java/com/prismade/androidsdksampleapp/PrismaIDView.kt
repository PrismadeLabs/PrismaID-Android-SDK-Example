package com.prismade.androidsdksampleapp

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import com.prismade.androidsdk.PLRecordingView
class PrismaIDView(context: Context, attributes: AttributeSet) : PLRecordingView(context, attributes) {

    @JavascriptInterface
    override fun onInitialisationResponse(response: String) {
        Log.d("PL-PRISMAID-VIEW-AJSI", "response: ${response}")

    }

    @JavascriptInterface
    override fun onInteractionResponse(response: String) {
        Log.d("PL-PRISMAID-VIEW-AJSI", "response: ${response}")
    }

    @JavascriptInterface
    override fun onProgressResponse(response: String) {
        Log.d("PL-PRISMAID-VIEW-AJSI", "response: ${response}")
    }

    @JavascriptInterface
    override fun onDetectionSuccessResponse(response: String) {
        Log.d("PL-PRISMAID-VIEW-AJSI", "response: ${response}")
        if(context is MainActivity)
        {
            val activity = context as MainActivity
            activity.onDecodeSuccess(response)
        }
    }

    @JavascriptInterface
    override fun onDetectionErrorResponse(response: String) {
        Log.d("PL-PRISMAID-VIEW-AJSI", "response: ${response}")
        if(context is MainActivity)
        {
            val activity = context as MainActivity
            activity.onDecodeError(response)
        }
    }
}