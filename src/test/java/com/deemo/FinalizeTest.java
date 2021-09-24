package com.deemo;

public class FinalizeTest {
	private static FinalizeTest SAVE_HOOK = null;

	private void isAlive() {
		System.out.println("Yeah, I'm still alive!");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize execute.");
		SAVE_HOOK = this;
	}

	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeTest();
		SAVE_HOOK = null;

		// 第一次拯救自己
		System.gc();
		// finalize 优先级较低，等待 500ms
		Thread.sleep(500);

		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("No, I am dead!");
		}

		SAVE_HOOK = null;
		// 第二次不会再调用 finalize
		System.gc();
		// finalize 优先级较低，等待 500ms
		Thread.sleep(500);

		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("No, I am dead!");
		}
	}

}
