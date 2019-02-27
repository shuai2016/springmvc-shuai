package xin.yangshuai.springmvc.thread;

/**
 * MyThread
 *
 * @author shuai
 * @date 2019/2/27
 */
public class MyThread {
	public static void main(String[] args) {
		MyRunnable run = new MyRunnable();
		run.setCount(0);
		for (int i = 0; i < 100000; i++) {
			new Thread(run).start();
		}
	}
}
