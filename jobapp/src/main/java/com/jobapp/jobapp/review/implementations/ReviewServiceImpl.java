package com.jobapp.jobapp.review.implementations;

import com.jobapp.jobapp.company.entities.Company;
import com.jobapp.jobapp.company.services.CompanyService;
import com.jobapp.jobapp.review.entities.Review;
import com.jobapp.jobapp.review.repositories.ReviewRepository;
import com.jobapp.jobapp.review.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.findById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Company company = companyService.findById(companyId);
        if (company != null) {
            review.setCompany(company);
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company company = companyService.findById(companyId);
        Optional<Review> review = reviewRepository.findById(reviewId);

        if (company != null && review.isPresent()) {
            company.getReviews().remove(review);
            review.get().setCompany(null);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
