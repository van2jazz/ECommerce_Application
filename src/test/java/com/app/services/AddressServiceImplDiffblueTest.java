package com.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.app.entities.Address;
import com.app.entities.Cart;
import com.app.entities.User;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.AddressDTO;
import com.app.repositories.AddressRepo;
import com.app.repositories.UserRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AddressServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AddressServiceImplDiffblueTest {
    @MockBean
    private AddressRepo addressRepo;

    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link AddressServiceImpl#createAddress(AddressDTO)}
     */
    @Test
    void testCreateAddress() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());
        when(addressRepo.findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(address);
        assertThrows(APIException.class, () -> addressServiceImpl.createAddress(new AddressDTO()));
        verify(addressRepo).findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any());
    }

    /**
     * Method under test:  {@link AddressServiceImpl#createAddress(AddressDTO)}
     */
    @Test
    void testCreateAddress2() {
        when(addressRepo.findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenThrow(new ResourceNotFoundException("Resource Name", "Field", "Field Name"));
        assertThrows(ResourceNotFoundException.class, () -> addressServiceImpl.createAddress(new AddressDTO()));
        verify(addressRepo).findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any());
    }

    /**
     * Method under test:  {@link AddressServiceImpl#getAddresses()}
     */
    @Test
    void testGetAddresses() {
        when(addressRepo.findAll()).thenReturn(new ArrayList<>());
        List<AddressDTO> actualAddresses = addressServiceImpl.getAddresses();
        verify(addressRepo).findAll();
        assertTrue(actualAddresses.isEmpty());
    }

    /**
     * Method under test: {@link AddressServiceImpl#getAddresses()}
     */
    @Test
    void testGetAddresses2() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());

        ArrayList<Address> addressList = new ArrayList<>();
        addressList.add(address);
        when(addressRepo.findAll()).thenReturn(addressList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any())).thenReturn(new AddressDTO());
        List<AddressDTO> actualAddresses = addressServiceImpl.getAddresses();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any());
        verify(addressRepo).findAll();
        assertEquals(1, actualAddresses.size());
    }

    /**
     * Method under test: {@link AddressServiceImpl#getAddresses()}
     */
    @Test
    void testGetAddresses3() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());

        Address address2 = new Address();
        address2.setAddressId(2L);
        address2.setBuildingName("com.app.entities.Address");
        address2.setCity("London");
        address2.setCountry("GBR");
        address2.setPincode("com.app.entities.Address");
        address2.setState("State");
        address2.setStreet("com.app.entities.Address");
        address2.setUsers(new ArrayList<>());

        ArrayList<Address> addressList = new ArrayList<>();
        addressList.add(address2);
        addressList.add(address);
        when(addressRepo.findAll()).thenReturn(addressList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any())).thenReturn(new AddressDTO());
        List<AddressDTO> actualAddresses = addressServiceImpl.getAddresses();
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any());
        verify(addressRepo).findAll();
        assertEquals(2, actualAddresses.size());
    }

    /**
     * Method under test: {@link AddressServiceImpl#getAddresses()}
     */
    @Test
    void testGetAddresses4() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());

        ArrayList<Address> addressList = new ArrayList<>();
        addressList.add(address);
        when(addressRepo.findAll()).thenReturn(addressList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any()))
                .thenThrow(new APIException("An error occurred"));
        assertThrows(APIException.class, () -> addressServiceImpl.getAddresses());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any());
        verify(addressRepo).findAll();
    }

    /**
     * Method under test: {@link AddressServiceImpl#getAddress(Long)}
     */
    @Test
    void testGetAddress() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());
        Optional<Address> ofResult = Optional.of(address);
        when(addressRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        AddressDTO addressDTO = new AddressDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any())).thenReturn(addressDTO);
        AddressDTO actualAddress = addressServiceImpl.getAddress(1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any());
        verify(addressRepo).findById(Mockito.<Long>any());
        assertSame(addressDTO, actualAddress);
    }

    /**
     * Method under test: {@link AddressServiceImpl#getAddress(Long)}
     */
    @Test
    void testGetAddress2() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());
        Optional<Address> ofResult = Optional.of(address);
        when(addressRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any()))
                .thenThrow(new APIException("An error occurred"));
        assertThrows(APIException.class, () -> addressServiceImpl.getAddress(1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any());
        verify(addressRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#getAddress(Long)}
     */
    @Test
    void testGetAddress3() {
        Optional<Address> emptyResult = Optional.empty();
        when(addressRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any())).thenReturn(new AddressDTO());
        assertThrows(ResourceNotFoundException.class, () -> addressServiceImpl.getAddress(1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(addressRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#updateAddress(Long, Address)}
     */
    @Test
    void testUpdateAddress() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());

        Address address2 = new Address();
        address2.setAddressId(1L);
        address2.setBuildingName("Building Name");
        address2.setCity("Oxford");
        address2.setCountry("GB");
        address2.setPincode("Pincode");
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setUsers(new ArrayList<>());
        Optional<Address> ofResult = Optional.of(address2);
        doNothing().when(addressRepo).deleteById(Mockito.<Long>any());
        when(addressRepo.findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(address);
        when(addressRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        AddressDTO addressDTO = new AddressDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any())).thenReturn(addressDTO);
        when(userRepo.findByAddress(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        Address address3 = new Address();
        address3.setAddressId(1L);
        address3.setBuildingName("Building Name");
        address3.setCity("Oxford");
        address3.setCountry("GB");
        address3.setPincode("Pincode");
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setUsers(new ArrayList<>());
        AddressDTO actualUpdateAddressResult = addressServiceImpl.updateAddress(1L, address3);
        verify(addressRepo).findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any());
        verify(userRepo, atLeast(1)).findByAddress(Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any());
        verify(addressRepo).deleteById(Mockito.<Long>any());
        verify(addressRepo).findById(Mockito.<Long>any());
        assertSame(addressDTO, actualUpdateAddressResult);
    }

    /**
     * Method under test: {@link AddressServiceImpl#updateAddress(Long, Address)}
     */
    @Test
    void testUpdateAddress2() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());
        when(addressRepo.findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(address);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AddressDTO>>any())).thenReturn(new AddressDTO());
        when(userRepo.findByAddress(Mockito.<Long>any())).thenThrow(new APIException("An error occurred"));

        Address address2 = new Address();
        address2.setAddressId(1L);
        address2.setBuildingName("Building Name");
        address2.setCity("Oxford");
        address2.setCountry("GB");
        address2.setPincode("Pincode");
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setUsers(new ArrayList<>());
        assertThrows(APIException.class, () -> addressServiceImpl.updateAddress(1L, address2));
        verify(addressRepo).findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any());
        verify(userRepo).findByAddress(Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#deleteAddress(Long)}
     */
    @Test
    void testDeleteAddress() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());
        Optional<Address> ofResult = Optional.of(address);
        doNothing().when(addressRepo).deleteById(Mockito.<Long>any());
        when(addressRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(userRepo.findByAddress(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        String actualDeleteAddressResult = addressServiceImpl.deleteAddress(1L);
        verify(userRepo).findByAddress(Mockito.<Long>any());
        verify(addressRepo).deleteById(Mockito.<Long>any());
        verify(addressRepo).findById(Mockito.<Long>any());
        assertEquals("Address deleted succesfully with addressId: 1", actualDeleteAddressResult);
    }

    /**
     * Method under test: {@link AddressServiceImpl#deleteAddress(Long)}
     */
    @Test
    void testDeleteAddress2() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());
        Optional<Address> ofResult = Optional.of(address);
        when(addressRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(userRepo.findByAddress(Mockito.<Long>any())).thenThrow(new APIException("An error occurred"));
        assertThrows(APIException.class, () -> addressServiceImpl.deleteAddress(1L));
        verify(userRepo).findByAddress(Mockito.<Long>any());
        verify(addressRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#deleteAddress(Long)}
     */
    @Test
    void testDeleteAddress3() {
        Optional<Address> emptyResult = Optional.empty();
        when(addressRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> addressServiceImpl.deleteAddress(1L));
        verify(addressRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#deleteAddress(Long)}
     */
    @Test
    void testDeleteAddress4() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());
        Optional<Address> ofResult = Optional.of(address);
        doNothing().when(addressRepo).deleteById(Mockito.<Long>any());
        when(addressRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Cart cart = new Cart();
        cart.setCartId(1L);
        cart.setCartItems(new ArrayList<>());
        cart.setTotalPrice(10.0d);
        cart.setUser(new User());

        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCart(cart);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setMobileNumber("42");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUserId(1L);

        Cart cart2 = new Cart();
        cart2.setCartId(1L);
        cart2.setCartItems(new ArrayList<>());
        cart2.setTotalPrice(10.0d);
        cart2.setUser(user);

        User user2 = new User();
        user2.setAddresses(new ArrayList<>());
        user2.setCart(cart2);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setMobileNumber("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new HashSet<>());
        user2.setUserId(1L);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);

        User user3 = new User();
        user3.setAddresses(new ArrayList<>());
        user3.setCart(new Cart());
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setLastName("Doe");
        user3.setMobileNumber("42");
        user3.setPassword("iloveyou");
        user3.setRoles(new HashSet<>());
        user3.setUserId(1L);

        Cart cart3 = new Cart();
        cart3.setCartId(1L);
        cart3.setCartItems(new ArrayList<>());
        cart3.setTotalPrice(10.0d);
        cart3.setUser(user3);

        User user4 = new User();
        user4.setAddresses(new ArrayList<>());
        user4.setCart(cart3);
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setLastName("Doe");
        user4.setMobileNumber("42");
        user4.setPassword("iloveyou");
        user4.setRoles(new HashSet<>());
        user4.setUserId(1L);

        Cart cart4 = new Cart();
        cart4.setCartId(1L);
        cart4.setCartItems(new ArrayList<>());
        cart4.setTotalPrice(10.0d);
        cart4.setUser(user4);

        User user5 = new User();
        user5.setAddresses(new ArrayList<>());
        user5.setCart(cart4);
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setLastName("Doe");
        user5.setMobileNumber("42");
        user5.setPassword("iloveyou");
        user5.setRoles(new HashSet<>());
        user5.setUserId(1L);
        when(userRepo.save(Mockito.<User>any())).thenReturn(user5);
        when(userRepo.findByAddress(Mockito.<Long>any())).thenReturn(userList);
        String actualDeleteAddressResult = addressServiceImpl.deleteAddress(1L);
        verify(userRepo).findByAddress(Mockito.<Long>any());
        verify(addressRepo).deleteById(Mockito.<Long>any());
        verify(addressRepo).findById(Mockito.<Long>any());
        verify(userRepo).save(Mockito.<User>any());
        assertEquals("Address deleted succesfully with addressId: 1", actualDeleteAddressResult);
    }
}
