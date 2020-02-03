package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
                                                    @RequestBody Comment comment) {
        if(reviewRepository.existsById(reviewId)) {
            comment.setReview(reviewRepository.findById(reviewId).get());
            return new ResponseEntity<Comment>(commentRepository.save(comment), HttpStatus.OK); // This will save a comment
        }
        else {
            return new ResponseEntity<String>("This review ID does not exist!", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        if(reviewRepository.existsById(reviewId)) {
             List<Comment> commentList = commentRepository.findAllByReviewId(reviewId);
            if (commentList == null || commentList.size() == 0) {
                return  new ResponseEntity<String>("This reviewId does not have any comment!", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK); // This will return the list of comments.
            }
        }



        throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
}