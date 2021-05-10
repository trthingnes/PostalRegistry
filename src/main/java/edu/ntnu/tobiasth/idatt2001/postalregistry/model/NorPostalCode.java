package edu.ntnu.tobiasth.idatt2001.postalregistry.model;

import java.util.Arrays;

/**
 * Norwegian postal code.
 *
 * Consists of 4 symbols which all are numbers 0-9.
 *
 * @author trthingnes
 */
public class NorPostalCode implements PostalCode {
    private static final String COUNTRY_NAME = "Norway";
    private final int postalCode;
    private final String postalName;
    private final int provinceCode;
    private final String provinceName;
    private final Type type;

    /**
     * Constructs a new Norwegian postal code.
     *
     * @param postalCode Integer postal code.
     * @param postalName Postal code name.
     * @param provinceCode Integer province code.
     * @param provinceName Province name.
     * @param type Postal code type char code.
     * @throws IllegalArgumentException If names are empty or codes are not integers.
     */
    public NorPostalCode(String postalCode, String postalName, String provinceCode, String provinceName, String type) throws IllegalArgumentException {
        if(postalName.isBlank() || provinceName.isBlank()) {
            throw new IllegalArgumentException("Postal and province names cannot be blank.");
        }

        try {
            this.postalCode = Integer.parseInt(postalCode);
            this.provinceCode = Integer.parseInt(provinceCode);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Norwegian postal and province codes have to be integers.");
        }

        this.postalName = postalName;
        this.provinceName = provinceName;
        this.type = Arrays.stream(Type.values())
                .filter(t -> t.code == type.charAt(0))
                .findAny().orElse(Type.UNKNOWN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsString() {
        return String.valueOf(postalCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return postalName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProvinceCode() {
        return String.valueOf(provinceCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCountryName() {
        return COUNTRY_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTypeDescription() {
        return type.description;
    }

    /**
     * Gets the postal code type.
     * @return Postal code type.
     */
    public Type getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        NorPostalCode that = (NorPostalCode) other;

        return postalCode == that.postalCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return postalCode;
    }

    /**
     * Returns a readable string version of the postal code.
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", postalCode, postalName, provinceName, COUNTRY_NAME);
    }

    /**
     * Postal code types. Shows what the postal codes are used for.
     */
    public enum Type {
        BOTH('B', "Used for both street addresses and P.O. boxes."),
        MIX('F', "Used for a mix of services."),
        STREET('G', "Used for street addresses."),
        BOX('P', "Used for P.O. boxes."),
        SERVICE('S', "Unused"),
        UNKNOWN('*', "Unknown");

        private final char code;
        private final String description;

        Type(char code, String description) {
            this.code = code;
            this.description = description;
        }

        public char getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }
}
