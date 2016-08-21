package ua.challenge.hibernate.examples.relationship.one.to.one.common.primary.keys.dao.impl;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.HibernateConfig;
import ua.challenge.hibernate.examples.relationship.one.to.one.common.primary.keys.dao.ProductDAO;
import ua.challenge.hibernate.examples.relationship.one.to.one.common.primary.keys.entity.Product;
import ua.challenge.hibernate.examples.relationship.one.to.one.common.primary.keys.entity.ProductDetail;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 8/21/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class ProductDAOImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ProductDAO productDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        Product product = new Product();
        product.setName("Civic");
        product.setDescription("Comfortable, fuel-saving car");
        product.setPrice(20000);

        ProductDetail detail = new ProductDetail();
        detail.setPartNumber("ABCDEFGHIJKL");
        detail.setDimension("2,5m x 1,4m x 1,2m");
        detail.setWeight(1000);
        detail.setManufacturer("Honda Automobile");
        detail.setOrigin("Japan");

        product.setProductDetail(detail);

        productDAO.save(product);

        Product currentProduct = (Product) sessionFactory.getCurrentSession().get(Product.class, 1L);
        assertThat(currentProduct).isNotNull();
        assertThat(currentProduct.getProductDetail()).isNotNull();
    }
}