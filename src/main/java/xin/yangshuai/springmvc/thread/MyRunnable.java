package xin.yangshuai.springmvc.thread;

/**
 * MyRunnable
 *
 * @author shuai
 * @date 2019/2/27
 */
public class MyRunnable implements Runnable {
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			count++;
			System.out.println(Thread.currentThread().getName()+" : "+(count));
		}
	}
}
