import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

//import org.ghost4j.analyzer.AnalysisItem;
//import org.ghost4j.analyzer.FontAnalyzer;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.SimpleRenderer;

public class ThumbGen {
	
	public ThumbGen(String file, String fileName) {
		
		try {
		
			PDFDocument document = new PDFDocument();
			document.load(new File(file));
			
			SimpleRenderer renderer = new SimpleRenderer();
			
			renderer.setResolution(300);
			//renderer.setAntialiasing(300);

			List<Image> images = renderer.render(document);
			//Image PDFImage = images.get(0);
			//Image thumbPDF = PDFImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
			
			File picName = new File(fileName + 1 + ".png");
			File thumbName = new File(fileName + 1 + "thumb.png");
			
			
			try {
				ImageIO.write((RenderedImage) images.get(0), "png", picName);
				BufferedImage originalImage = ImageIO.read(new File(picName.getAbsolutePath()));
				ImageIO.write((RenderedImage)resizeImage(originalImage, 3), "png", thumbName);


			} catch (IOException e) {
				System.out.println("ERROR1: " + e.getMessage());
			}
		
		} catch (Exception e) {
			System.out.println("ERROR2: " + e.getMessage());
		}
	
	}
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		
		int IMG_WIDTH = originalImage.getWidth() / 8;
		int IMG_HEIGHT = originalImage.getHeight() / 8;
		 
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
		
	}

}