package com.example.chinese_chess.controller.ImageController;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class ImageSet {
    public static ImageView createImageView(String image_location, double width, double height) {
        try {
            // Tạo InputStream từ đường dẫn
            InputStream imageStream = ImageSet.class.getResourceAsStream(image_location);

            // Kiểm tra xem InputStream có null không
            if (imageStream == null) {
                System.err.println("Không tìm thấy tệp hình ảnh tại đường dẫn: " + image_location);
                return null; // Xử lý lỗi theo logic ứng dụng của bạn
            }

            // Tạo hình ảnh từ InputStream
            Image image = new Image(imageStream);

            // Tạo ImageView và đặt Image
            ImageView imageView = new ImageView();
            imageView.setImage(image);

            // Thiết lập các thuộc tính của ImageView
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);

            // Đóng InputStream
            imageStream.close();

            return imageView;
        } catch (Exception e) {
            // In ra lỗi
            System.err.println("Lỗi khi tải hình ảnh: " + e.getMessage());
            e.printStackTrace();

            // Trả về null hoặc xử lý lỗi theo logic ứng dụng của bạn
            return null;
        }
    }

    public static ImageView createImageView(String image_location, boolean background) {
        ImageView image_view = new ImageView();
        try {
            // Attempt to load the image
            InputStream imageStream = ImageSet.class.getResourceAsStream(image_location);

            // Check if the imageStream is null
            if (imageStream == null) {
                throw new FileNotFoundException("Image file not found at path: " + image_location);
            }

            // Create the image and set it to the ImageView
            Image image = new Image(imageStream);
            image_view.setImage(image);
            image_view.relocate(50, 50);

            // Set dimensions based on background flag
            if (background) {
                image_view.setFitWidth(45 * 9);
                image_view.setFitHeight(45 * 10);
            } else {
                image_view.setFitWidth(45);
                image_view.setFitHeight(45);
                image_view.setX(0);
                image_view.setY(0);
            }

            // Set additional properties
            image_view.setPreserveRatio(true);
            image_view.setSmooth(true);
            image_view.setCache(true);

        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            // Return null or handle the error according to your application logic
            return null;
        }
        return image_view;
    }

}
