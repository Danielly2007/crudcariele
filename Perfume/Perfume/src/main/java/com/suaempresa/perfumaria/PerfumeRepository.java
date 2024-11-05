package com.suaempresa.perfumaria;

import org.springframework.data.jpa.repository.JpaRepository;

import model.PerfumeModel;

public interface PerfumeRepository extends JpaRepository<PerfumeModel, Long> {
}

