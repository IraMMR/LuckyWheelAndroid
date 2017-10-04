# LuckyWheelAndroid
Custom view - rotating Lucky Wheel with variable number of options. Rotation are caused by fling gesture. 

Lucky Wheel is a custom view. It's sectors are drawn on the Canvas.

In order to implement gesture detection, onFling() method of GestureDetector.SimpleOnGestureListener is overwritten.
It is taken into account velocity of the fling at the and of the gesture and how far from the wheel center the gesture was happened.
The faster (the farther from the center) - the higher initial rotation speed.

To implement animation ObjectAnimator with DecelerateInterpolator is used.

<img src="https://github.com/IraMMR/LuckyWheelAndroid/blob/master/app/src/main/res/drawable/Circular_Lucky_Wheel_demo.gif" alt="">
