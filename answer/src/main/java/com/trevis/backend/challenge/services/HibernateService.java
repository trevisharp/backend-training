package com.trevis.backend.challenge.services;

import org.hibernate.Session;

public interface HibernateService {
    Session getSession();
}
