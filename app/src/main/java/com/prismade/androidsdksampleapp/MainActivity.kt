package com.prismade.androidsdksampleapp

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private var card: ImageView? = null
    private var thumb: ImageView? = null
    private var hand: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        card = findViewById(R.id.card)
        thumb = findViewById(R.id.thumb)
        hand = findViewById(R.id.hand)
        val view: PrismaIDView = findViewById(R.id.prismaidView)
        view.initialiseSDK("36gap5I6Cb8FZ49q12brK8FNGSnHfgcY9XnBeWRg", "https://api-dev.prismade.net/prismaid", false )
        animateCard()
    }

    private fun animateCard(){
        val displayMetrics = resources.displayMetrics
        card?.scaleX = 1.2f
        card?.scaleY = 1.2f
        val translate1 = ObjectAnimator.ofFloat(card, View.TRANSLATION_X, displayMetrics.widthPixels.toFloat(), 0f)
        val translate2 = ObjectAnimator.ofFloat(card, View.TRANSLATION_Y, displayMetrics.heightPixels.toFloat(), 0f)
        val scale1 = ObjectAnimator.ofFloat(card, View.SCALE_X, 1.2f, 1.0f)
        val scale2 = ObjectAnimator.ofFloat(card, View.SCALE_Y, 1.2f, 1.0f)
        val set1 = AnimatorSet().apply {
            playTogether(translate1, translate2)
            duration = 500
        }
        val set2 = AnimatorSet().apply {
            playTogether(scale1, scale2)
            duration = 200
        }
        AnimatorSet().apply {
            playSequentially(set1, set2)
            start()
        }.addListener((object : Animator.AnimatorListener{
            override fun onAnimationEnd(animation: Animator?) {
                animateThumb()
            }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}

        }))
    }

    private fun animateThumb(){
        val displayMetrics = resources.displayMetrics
        thumb?.scaleX = 1.2f
        thumb?.scaleY = 1.2f
        thumb?.alpha = 1.0f
        val translate1 = ObjectAnimator.ofFloat(thumb, View.TRANSLATION_X, -displayMetrics.widthPixels.toFloat(), card?.x!! - (thumb?.width!!*0.65f))
        val translate2 = ObjectAnimator.ofFloat(thumb, View.TRANSLATION_Y, displayMetrics.heightPixels.toFloat(), card?.y!!  + (card?.height!!*0.85f))
        val scale1 = ObjectAnimator.ofFloat(thumb, View.SCALE_X, 1.2f, 1.0f)
        val scale2 = ObjectAnimator.ofFloat(thumb, View.SCALE_Y, 1.2f, 1.0f)
        val set1 = AnimatorSet().apply {
            playTogether(translate1, translate2)
            duration = 500
        }
        val set2 = AnimatorSet().apply {
            playTogether(scale1, scale2)
            duration = 200
        }
        AnimatorSet().apply {
            playSequentially(set1, set2)
            start()
        }.addListener((object : Animator.AnimatorListener{
            override fun onAnimationEnd(animation: Animator?) {
                animateHand()
            }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}

        }))

    }

    private fun animateHand(){
        val displayMetrics = resources.displayMetrics
        hand?.scaleX = 1.2f
        hand?.scaleY = 1.2f
        hand?.alpha = 1.0f

        val translate1 = ObjectAnimator.ofFloat(hand, View.TRANSLATION_X, displayMetrics.widthPixels.toFloat(), card?.x!! + (card?.width!!*0.45f))
        val translate2 = ObjectAnimator.ofFloat(hand, View.TRANSLATION_Y, displayMetrics.heightPixels.toFloat(), card?.y!!  + (card?.height!!*0.8f))
        val scale1 = ObjectAnimator.ofFloat(hand, View.SCALE_X, 1.2f, 1.0f)
        val scale2 = ObjectAnimator.ofFloat(hand, View.SCALE_Y, 1.2f, 1.0f)
        val translate3 = ObjectAnimator.ofFloat(hand, View.TRANSLATION_Y, card?.y!!  + (card?.height!!*0.8f), card?.y!!- (hand?.height!!*0.1f))
        val scale3 = ObjectAnimator.ofFloat(hand, View.SCALE_X, 1.0f, 1.2f)
        val scale4 = ObjectAnimator.ofFloat(hand, View.SCALE_Y, 1.0f, 1.2f)
        val translate4 = ObjectAnimator.ofFloat(hand, View.TRANSLATION_X, card?.x!! + (card?.width!!*0.45f), displayMetrics.widthPixels.toFloat()*1.5f)
        translate3.duration = 1000
        translate4.duration = 500
        val set1 = AnimatorSet().apply {
            playTogether(translate1, translate2)
            duration = 500
        }
        val set2 = AnimatorSet().apply {
            playTogether(scale1, scale2)
            duration = 200
        }
        val set3 = AnimatorSet().apply {
            playTogether(scale3, scale4)
            duration = 200
        }
        val completeHandAnimation = AnimatorSet().apply {
            playSequentially(set1, set2, translate3, set3, translate4)
            start()
        }
        completeHandAnimation.addListener((object : Animator.AnimatorListener{
            override fun onAnimationEnd(animation: Animator?) {
                hand?.scaleX = 1.2f
                hand?.scaleY = 1.2f
                completeHandAnimation.start()
            }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}

        }))

    }

    fun onDecodeSuccess(response: String){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Decode Success")
        alertDialogBuilder.setMessage(response)
        alertDialogBuilder.show()
    }

    fun onDecodeError(response: String){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Decode Error")
        alertDialogBuilder.setMessage(response)
        alertDialogBuilder.show()
    }


}
