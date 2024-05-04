package com.jans.model.view.test.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Handler
import android.view.animation.RotateAnimation
import android.widget.RelativeLayout
import java.io.InputStreamReader


class Config {

    companion object{





        fun goToScreen(context: Context, classActivity: Class<*>){
            context.startActivity(Intent(context, classActivity))
        }



        fun readJsonFileWithGson(context: Context, rawFileId: Int): String {
                val inputStream = context.resources.openRawResource(rawFileId)
                val reader = InputStreamReader(inputStream)
                return reader.readText()
            }


        class LoadingIndicatorView(private val context: Context, var radius: Int) :
            RelativeLayout(
                context
            ) {
            private val numberOfBars = 12

            var arrBars: ArrayList<LoadingIndicatorBarView>? = null

            private var isAnimating = false
            private var currentFrame = 0

            private val handler: Handler = Handler()
            private var playFrameRunnable: Runnable? = null

            init {
                initViews()
                initLayouts()
                addViews()
                spreadBars()
            }

            private fun initViews() {
                arrBars = ArrayList<LoadingIndicatorBarView>()

                for (i in 0 until numberOfBars) {
                    val bar: LoadingIndicatorBarView =
                        LoadingIndicatorBarView(context, radius / 10.0f)

                    arrBars!!.add(bar)
                }
            }

            private fun initLayouts() {
                for (i in 0 until numberOfBars) {
                    val bar: LoadingIndicatorBarView = arrBars!![i]

                    bar.setId(generateViewId())

                    val barLayoutParams = LayoutParams(
                        (radius / 5.0f).toInt(),
                        (radius / 2.0f).toInt()
                    )

                    barLayoutParams.addRule(ALIGN_PARENT_TOP)
                    barLayoutParams.addRule(CENTER_HORIZONTAL)

                    bar.layoutParams = barLayoutParams
                }
            }

            private fun addViews() {
                for (i in 0 until numberOfBars) {
                    val bar: LoadingIndicatorBarView = arrBars!![i]

                    addView(bar)
                }
            }

            private fun spreadBars() {
                var degrees = 0

                for (i in arrBars!!.indices) {
                    val bar: LoadingIndicatorBarView = arrBars!![i]

                    rotateBar(bar, degrees.toFloat())

                    degrees += 30
                }
            }

            private fun rotateBar(bar: LoadingIndicatorBarView, degrees: Float) {
                val animation = RotateAnimation(0f, degrees, radius / 10.0f, radius.toFloat())
                animation.duration = 0
                animation.fillAfter = true

                bar.animation = animation
                animation.start()
            }

            fun startAnimating() {
                alpha = 1.0f

                isAnimating = true

                playFrameRunnable = Runnable { playFrame() }

                // recursive function until isAnimating is false
                playFrame()
            }

            fun stopAnimating() {
                isAnimating = false

                alpha = 0.0f

                invalidate()

                playFrameRunnable = null
            }

            private fun playFrame() {
                if (isAnimating) {
                    resetAllBarAlpha()
                    updateFrame()

                    handler.postDelayed(playFrameRunnable!!, 100)
                }
            }

            private fun updateFrame() {
                if (isAnimating) {
                    showFrame(currentFrame)
                    currentFrame += 1

                    if (currentFrame > 11) {
                        currentFrame = 0
                    }
                }
            }

            private fun resetAllBarAlpha() {
                var bar: LoadingIndicatorBarView? = null

                for (i in arrBars!!.indices) {
                    bar = arrBars!![i]

                    bar.alpha = 0.5f
                }
            }

            private fun showFrame(frameNumber: Int) {
                val indexes = getFrameIndexesForFrameNumber(frameNumber)

                gradientColorBarSets(indexes)
            }

            private fun getFrameIndexesForFrameNumber(frameNumber: Int): IntArray {
                return if (frameNumber == 0) {
                    indexesFromNumbers(0, 11, 10, 9)
                } else if (frameNumber == 1) {
                    indexesFromNumbers(1, 0, 11, 10)
                } else if (frameNumber == 2) {
                    indexesFromNumbers(2, 1, 0, 11)
                } else if (frameNumber == 3) {
                    indexesFromNumbers(3, 2, 1, 0)
                } else if (frameNumber == 4) {
                    indexesFromNumbers(4, 3, 2, 1)
                } else if (frameNumber == 5) {
                    indexesFromNumbers(5, 4, 3, 2)
                } else if (frameNumber == 6) {
                    indexesFromNumbers(6, 5, 4, 3)
                } else if (frameNumber == 7) {
                    indexesFromNumbers(7, 6, 5, 4)
                } else if (frameNumber == 8) {
                    indexesFromNumbers(8, 7, 6, 5)
                } else if (frameNumber == 9) {
                    indexesFromNumbers(9, 8, 7, 6)
                } else if (frameNumber == 10) {
                    indexesFromNumbers(10, 9, 8, 7)
                } else {
                    indexesFromNumbers(11, 10, 9, 8)
                }
            }

            private fun indexesFromNumbers(i1: Int, i2: Int, i3: Int, i4: Int): IntArray {
                val indexes = intArrayOf(i1, i2, i3, i4)
                return indexes
            }

            private fun gradientColorBarSets(indexes: IntArray) {
                var alpha = 1.0f

                var barView: LoadingIndicatorBarView? = null

                for (i in indexes.indices) {
                    val barIndex = indexes[i]

                    barView = arrBars!![barIndex]


                    barView.alpha = alpha
                    alpha -= 0.125f
                }

                invalidate()
            }
        }


        @SuppressLint("ViewConstructor")
        class LoadingIndicatorBarView(
            context: Context,
            private val cornerRadius: Float
        ) :
            RelativeLayout(context) {
            init {
                initViews()
            }

            private fun initViews() {
                background = ToolBox.roundedCornerRectWithColor(
                    Color.BLACK, cornerRadius
                )

                alpha = 0.5f
            }

            fun resetColor() {
                background = ToolBox.roundedCornerRectWithColor(
                    Color.argb(255, 255, 255, 255), cornerRadius
                )

                alpha = 0.5f
            }
        }

        @SuppressLint("StaticFieldLeak")
        class ToolBox
        private constructor() {
            var context: Context? = null

            companion object {
                @get:Synchronized
                var instance: ToolBox? = null
                    get() {
                        if (field == null) {
                            field = ToolBox()
                        }

                        return field
                    }
                    private set

                fun roundedCornerRectOutlineWithColor(
                    color: Int, cornerRadius: Float,
                    strokeWidth: Float
                ): ShapeDrawable {
                    val radii = floatArrayOf(
                        cornerRadius, cornerRadius,
                        cornerRadius, cornerRadius,
                        cornerRadius, cornerRadius,
                        cornerRadius, cornerRadius
                    )

                    val roundedCornerShape = RoundRectShape(radii, null, null)

                    val shape = ShapeDrawable()
                    shape.paint.color = color
                    shape.shape = roundedCornerShape
                    shape.paint.strokeWidth = strokeWidth
                    shape.paint.style = Paint.Style.STROKE

                    return shape
                }

                fun roundedCornerRectWithColor(color: Int, cornerRadius: Float): ShapeDrawable {
                    val radii = floatArrayOf(
                        cornerRadius, cornerRadius,
                        cornerRadius, cornerRadius,
                        cornerRadius, cornerRadius,
                        cornerRadius, cornerRadius
                    )

                    val roundedCornerShape = RoundRectShape(radii, null, null)

                    val shape = ShapeDrawable()
                    shape.paint.color = color
                    shape.shape = roundedCornerShape

                    return shape
                }

                fun roundedCornerRectWithColor(
                    color: Int,
                    topLeftRadius: Float,
                    topRightRadius: Float,
                    bottomRightRadius: Float,
                    bottomLeftRadius: Float
                ): ShapeDrawable {
                    val radii = floatArrayOf(
                        topLeftRadius, topLeftRadius,
                        topRightRadius, topRightRadius,
                        bottomRightRadius, bottomRightRadius,
                        bottomLeftRadius, bottomLeftRadius
                    )

                    val roundedCornerShape = RoundRectShape(radii, null, null)

                    val shape = ShapeDrawable()
                    shape.paint.color = color
                    shape.shape = roundedCornerShape

                    return shape
                }

                val screenWidth: Int
                    get() = Resources.getSystem().displayMetrics.widthPixels

                val screenHeight: Int
                    get() = Resources.getSystem().displayMetrics.heightPixels

                fun getScreenOrientation(context: Context): Int {
                    return context.resources.configuration.orientation
                }

                fun isLandscapeOrientation(context: Context): Boolean {
                    return getScreenOrientation(context) == Configuration.ORIENTATION_LANDSCAPE
                }
            }
        }




        }


}