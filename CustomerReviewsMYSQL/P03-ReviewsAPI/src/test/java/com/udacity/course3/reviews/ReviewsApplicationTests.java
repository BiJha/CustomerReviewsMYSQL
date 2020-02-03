package com.udacity.course3.reviews;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Timestamp;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	CommentRepository commentRepository;

	@Before
	public void setup() {
		Product product = new Product();
		Review review = new Review();
		Comment comment = new Comment();

		product.setName("Test Product Name");
		productRepository.save(product);

		review.setRating(3);
		review.setReview_content("Test Review Content");
		review.setReview_user_name("someone");
		review.setProduct(product);
		reviewRepository.save(review);


		comment.setComment_content("Test Comment Content");
		comment.setReview(review);
		commentRepository.save(comment);

	}

	@Test
	public void contextLoads() {
	}

//This will test the Product Entity
	@Test
	public void productEntityTest(){
		Product product = productRepository.findAll().get(0);
		assertEquals("Test Product Name", product.getName());
	}

//	This will test the Review Entity

	@Test
	public void reviewEntityTest(){
		Review review = reviewRepository.findAll().get(0);
		assertEquals("Test Review Content", review.getReview_content());
	}

//	This will test the Comment Entity

	@Test
	public void commentEntityTest(){
		Comment comment = commentRepository.findAll().get(0);
		assertEquals("Test Comment Content", comment.getComment_content());
	}

}