package es.devcircus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.devcircus.model.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long> {

}
