package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;

import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {
        PhoneNumber[] phoneNumbers = new PhoneNumber[phoneNumberCount];
        for (int i = 0; i < phoneNumberCount; i++) {
            phoneNumbers[i] = createRandomPhoneNumber();
        }
        return phoneNumbers;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber() {
        Integer areaCode = generateRandomNumber(100, 999);
        Integer centralOfficeCode = generateRandomNumber(100, 999);
        Integer phoneLineCode = generateRandomNumber(1000, 9999);
//        System.out.print(areaCode);
//        System.out.print(centralOfficeCode);
//        System.out.println(phoneLineCode);

        return createPhoneNumberSafely(areaCode, centralOfficeCode, phoneLineCode);
    }

    public static Integer generateRandomNumber(Integer min, Integer max) {
        Integer range = max - min;
        return (int) Math.floor(Math.random() * range) + min;
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {
        String number = String.format("(%s)-%s-%s", areaCode, centralOfficeCode, phoneLineCode);
        PhoneNumber phone;
        try {
            phone = createPhoneNumber(number);
        } catch(InvalidPhoneNumberFormatException e) {
            logger.warning(String.format("%s is not a valid phone number", number));
            phone = null;
        }
        return phone;

    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {
        logger.info(String.format("Attempting to create a new PhoneNumber object of %s", phoneNumberString));
        return new PhoneNumber(phoneNumberString);
    }
}
