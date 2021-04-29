package guru.springframework.springmvcrestv2.services.imp;

import guru.springframework.springmvcrestv2.api.v1.VendorDTO;
import guru.springframework.springmvcrestv2.api.v1.mapper.VendorMapper;
import guru.springframework.springmvcrestv2.bootstrap.Bootstrap;
import guru.springframework.springmvcrestv2.repositories.CategoryRepository;
import guru.springframework.springmvcrestv2.repositories.CustomerRepository;
import guru.springframework.springmvcrestv2.repositories.VendorRepository;
import guru.springframework.springmvcrestv2.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static guru.springframework.springmvcrestv2.constants.CoreConstants.BASE_VENDOR_URL;
import static guru.springframework.springmvcrestv2.services.imp.VendorServiceImplTest.ID;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VendorServiceIT {

    public static final String UPDATED_NAME = "updated name";

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    VendorService vendorService;

    Bootstrap bootstrap;

    @Before
    public void setUp() throws Exception {
        bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void patchName() {
        Long givenId = ID;
        VendorDTO originalVendor = vendorService.getById(givenId);

        VendorDTO givenVendor = new VendorDTO();
        givenVendor.setName(UPDATED_NAME);

        VendorDTO result = vendorService.patch(givenId, givenVendor);
        assertNotNull(result);
        assertEquals(BASE_VENDOR_URL + "/" + givenId, result.getUrl());
        assertThat(result.getName(), not(equalTo(originalVendor.getName())));
    }
}
