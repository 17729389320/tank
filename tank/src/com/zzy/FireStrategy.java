package com.zzy;

/**
 * 策略模式定义了一系列的算法，并将每一个算法封装起来，使每个算法可以相互替代，使算法本身和使用算法的客户端分割开来，相互独立。
 * 作用：把具体的算法实现从业务逻辑中剥离出来，成为一系列独立算法类，使得它们可以相互替换。
 * @author zhaozhanyang
 *
 */
public interface FireStrategy {
	void fire(Tank t);
}
