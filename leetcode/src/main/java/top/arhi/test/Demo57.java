package top.arhi.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

public class Demo57 {
	/**
	 * 获取视频中的图片
	 * @param inputStream 视频输入流
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage grabberVideoFramer(InputStream inputStream) throws Exception {
		// 最后获取到的视频的图片缓存
		BufferedImage bufferedImage = null;
		// Frame对象
		Frame frame = null;
		// 标识
		int flag = 0;
		FFmpegFrameGrabber fFmpegFrameGrabber = null;
		try {
			//获取视频文件
			fFmpegFrameGrabber = new FFmpegFrameGrabber(inputStream);
			fFmpegFrameGrabber.start();

			// 获取视频总帧数
			int ftp = fFmpegFrameGrabber.getLengthInFrames();
			System.out.println("时长 " + ftp / fFmpegFrameGrabber.getFrameRate() / 60);

			//对视屏 帧数处理
			while (flag <= ftp) {
				frame = fFmpegFrameGrabber.grabImage();
				//对视频的第10帧进行处理
				if (frame != null && flag == 10) {
					// 图片缓存对象
					bufferedImage = FrameToBufferedImage(frame);
					break;
				}
				flag++;
			}
		}finally {
			if(fFmpegFrameGrabber != null) {
				fFmpegFrameGrabber.stop();
				fFmpegFrameGrabber.close();
			}
		}
		return bufferedImage;
	}

	private static BufferedImage FrameToBufferedImage(Frame frame) {
		// 创建BufferedImage对象
		Java2DFrameConverter converter = new Java2DFrameConverter();
		BufferedImage bufferedImage = converter.getBufferedImage(frame);
		return bufferedImage;
	}


	public static void main(String[] args) {
		//视频路径
		String videoFileName = "D:\\media\\movie\\不能错过的只有你.mp4";
		InputStream intInputStream = null;
		//输出的图片路径
		File outPut = new File("D:\\media\\image\\不能错过的只有你.jpg");
		try {
			intInputStream = new FileInputStream(new File(videoFileName));
			BufferedImage bufferedImage = grabberVideoFramer(intInputStream);
			ImageIO.write(bufferedImage, "jpg", outPut);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(intInputStream !=null) {
				try {
					intInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
