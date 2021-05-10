/**
 * Interface for postal codes.
 *
 * Many countries use combinations of letters and numbers. Interface therefore uses string type
 *
 * @author trthingnes
 */
public interface PostalCode {
    /**
     * Gets the postal code as a separate string.
     * @return Postal code as string.
     */
    String getAsString();

    /**
     * Gets the postal code location name.
     * @return Location name.
     */
    String getName();

    /**
     * Gets the postal code province code.
     * @return Province code.
     */
    String getProvinceCode();

    /**
     * Gets the postal code province name.
     * @return Province name.
     */
    String getProvinceName();

    /**
     * Gets the postal code country code.
     * @return Country code.
     */
    String getCountryCode();

    /**
     * Gets a print friendly postal code country name.
     * @return Country name.
     */
    String getCountryName();

    /**
     * Gets the postal code type describing code use areas.
     * @return Type description.
     */
    String getTypeDescription();

    /**
     * Get whether or not two postal codes are equal.
     * @param other Other postal code.
     * @return True if the postal codes are equal, false if not.
     */
    boolean equals(Object other);
}
