package com.example.constraintlayout

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mercurio = findViewById<ImageView>(R.id.mercurio)
        val venus = findViewById<ImageView>(R.id.venus)
        val tierra = findViewById<ImageView>(R.id.tierra)
        val mars = findViewById<ImageView>(R.id.marte)
        val jupiter = findViewById<ImageView>(R.id.jupiter)
        val saturno = findViewById<ImageView>(R.id.saturno)
        val urano = findViewById<ImageView>(R.id.urano)
        val neptuno = findViewById<ImageView>(R.id.neptuno)

        val marteAnim = ValueAnimator.ofInt(0, 359)
        marteAnim.addUpdateListener{ value ->
            val animatedValue = value.animatedValue as Int

            val layoutParams = mars.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = animatedValue.toFloat()

            mars.layoutParams = layoutParams

        }
        marteAnim.repeatMode = ValueAnimator.RESTART
        marteAnim.repeatCount = ValueAnimator.INFINITE
        marteAnim.interpolator = LinearInterpolator()
        marteAnim.duration = 8000

        val mercurioAnim = ValueAnimator.ofInt(0, 359)
        mercurioAnim.addUpdateListener{ value ->
            val animatedValue = value.animatedValue as Int

            val layoutParams = mercurio.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = animatedValue.toFloat()

            mercurio.layoutParams = layoutParams

        }
        mercurioAnim.repeatMode = ValueAnimator.RESTART
        mercurioAnim.repeatCount = ValueAnimator.INFINITE
        mercurioAnim.interpolator = LinearInterpolator()
        mercurioAnim.duration = 2000

        val venusAnim = ValueAnimator.ofInt(0, 359)
        venusAnim.addUpdateListener{ value ->
            val animatedValue = value.animatedValue as Int

            val layoutParams = venus.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = animatedValue.toFloat()

            venus.layoutParams = layoutParams

        }
        venusAnim.repeatMode = ValueAnimator.RESTART
        venusAnim.repeatCount = ValueAnimator.INFINITE
        venusAnim.interpolator = LinearInterpolator()
        venusAnim.duration = 4200

        val tierraAnim = ValueAnimator.ofInt(0, 359)
        tierraAnim.addUpdateListener{ value ->
            val animatedValue = value.animatedValue as Int

            val layoutParams = tierra.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = animatedValue.toFloat()

            tierra.layoutParams = layoutParams

        }
        tierraAnim.repeatMode = ValueAnimator.RESTART
        tierraAnim.repeatCount = ValueAnimator.INFINITE
        tierraAnim.interpolator = LinearInterpolator()
        tierraAnim.duration = 5000

        val jupiterAnim = ValueAnimator.ofInt(0, 359)
        jupiterAnim.addUpdateListener{ value ->
            val animatedValue = value.animatedValue as Int

            val layoutParams = jupiter.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = animatedValue.toFloat()

            jupiter.layoutParams = layoutParams

        }
        jupiterAnim.repeatMode = ValueAnimator.RESTART
        jupiterAnim.repeatCount = ValueAnimator.INFINITE
        jupiterAnim.interpolator = LinearInterpolator()
        jupiterAnim.duration = 10000

        val saturnoAnim = ValueAnimator.ofInt(0, 359)
        saturnoAnim.addUpdateListener{ value ->
            val animatedValue = value.animatedValue as Int

            val layoutParams = saturno.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = animatedValue.toFloat()

            saturno.layoutParams = layoutParams

        }
        saturnoAnim.repeatMode = ValueAnimator.RESTART
        saturnoAnim.repeatCount = ValueAnimator.INFINITE
        saturnoAnim.interpolator = LinearInterpolator()
        saturnoAnim.duration = 15000

        val uranoAnim = ValueAnimator.ofInt(0, 359)
        uranoAnim.addUpdateListener{ value ->
            val animatedValue = value.animatedValue as Int

            val layoutParams = urano.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = animatedValue.toFloat()

            urano.layoutParams = layoutParams

        }
        uranoAnim.repeatMode = ValueAnimator.RESTART
        uranoAnim.repeatCount = ValueAnimator.INFINITE
        uranoAnim.interpolator = LinearInterpolator()
        uranoAnim.duration = 14000

        val neptunoAnim = ValueAnimator.ofInt(0, 359)
        neptunoAnim.addUpdateListener{ value ->
            val animatedValue = value.animatedValue as Int

            val layoutParams = neptuno.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = animatedValue.toFloat()

            neptuno.layoutParams = layoutParams

        }
        neptunoAnim.repeatMode = ValueAnimator.RESTART
        neptunoAnim.repeatCount = ValueAnimator.INFINITE
        neptunoAnim.interpolator = LinearInterpolator()
        neptunoAnim.duration = 20000

        val set = AnimatorSet()
        set.playTogether(mercurioAnim,venusAnim,tierraAnim,marteAnim,jupiterAnim,saturnoAnim,uranoAnim,neptunoAnim)
        set.start()

        /*animator.set

        val mercurioAnim = ObjectAnimator.ofFloat(mercurio, "circleAngle", 359f)
        mercurioAnim.repeatMode = ValueAnimator.RESTART
        mercurioAnim.repeatCount = ValueAnimator.INFINITE
        mercurioAnim.interpolator = LinearInterpolator()
        mercurioAnim.duration = 2000

        val venusAnim = ObjectAnimator.ofFloat(venus, "circleAngle", 359f)
        venusAnim.repeatMode = ValueAnimator.RESTART
        venusAnim.repeatCount = ValueAnimator.INFINITE
        venusAnim.interpolator = LinearInterpolator()
        venusAnim.duration = 1000

        val tierraAnim = ObjectAnimator.ofFloat(tierra, "circleAngle", 359f)
        tierraAnim.repeatMode = ValueAnimator.RESTART
        tierraAnim.repeatCount = ValueAnimator.INFINITE
        tierraAnim.interpolator = LinearInterpolator()
        tierraAnim.duration = 5000

        val marteAnim = ObjectAnimator.ofFloat(mars, "circleAngle", 359f)
        marteAnim.repeatMode = ValueAnimator.RESTART
        marteAnim.repeatCount = ValueAnimator.INFINITE
        marteAnim.interpolator = LinearInterpolator()
        marteAnim.duration = 4000

        val jupiterAnim = ObjectAnimator.ofFloat(jupiter, "circleAngle", 359f)
        jupiterAnim.repeatMode = ValueAnimator.RESTART
        jupiterAnim.repeatCount = ValueAnimator.INFINITE
        jupiterAnim.interpolator = LinearInterpolator()
        jupiterAnim.duration = 1500

        val saturnoAnim = ObjectAnimator.ofFloat(saturno, "circleAngle", 359f)
        saturnoAnim.repeatMode = ValueAnimator.RESTART
        saturnoAnim.repeatCount = ValueAnimator.INFINITE
        saturnoAnim.interpolator = LinearInterpolator()
        saturnoAnim.duration = 10000

        val uranoAnim = ObjectAnimator.ofFloat(urano, "circleAngle", 359f)
        uranoAnim.repeatMode = ValueAnimator.RESTART
        uranoAnim.repeatCount = ValueAnimator.INFINITE
        uranoAnim.interpolator = LinearInterpolator()
        uranoAnim.duration = 14000

        val neptunoAnim = ObjectAnimator.ofFloat(neptuno, "circleAngle", 359f)
        neptunoAnim.repeatMode = ValueAnimator.RESTART
        neptunoAnim.repeatCount = ValueAnimator.INFINITE
        neptunoAnim.interpolator = LinearInterpolator()
        neptunoAnim.duration = 15000

        set.playTogether(mercurioAnim,venusAnim,tierraAnim,marteAnim,jupiterAnim,saturnoAnim,uranoAnim,neptunoAnim)
        set.start()
        */

    }
}