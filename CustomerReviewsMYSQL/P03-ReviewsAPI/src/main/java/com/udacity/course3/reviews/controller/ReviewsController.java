package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;
import java.util.List;


/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {


    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId,
                                                    @RequestBody Review review) {

        if (productRepository.existsById(productId)) {
            review.setProduct(productRepository.findById(productId).get());
            return new ResponseEntity<Review>(reviewRepository.save(review), HttpStatus.OK); // This will return the review of a product
        } else {
            return new ResponseEntity<String>("This product Id does not exist", HttpStatus.NOT_FOUND);

        }
    }



        /**
         * Lists reviews by product.
         *
         * @param productId The id of the product.
         * @return The list of reviews.
         */

        @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
        public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
            if (productRepository.existsById(productId)) {
                return new ResponseEntity<List<?>>(reviewRepository.findByProductId(productId), HttpStatus.OK);
            } // This will return the list of reviews.
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);

        }



        }
