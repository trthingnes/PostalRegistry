import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.tobiasth.idatt2001.postalregistry.NorPostalCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NorPostalCodeTest {
  private final String postalCode = "7034";
  private final String postalName = "Trondheim";
  private final String provinceCode = "7001";
  private final String provinceName = "Trondheim";
  private final String type = "G";

  private final String illegalPostalCode = "XYZ123";
  private final String illegalPostalName = "";

  @Test
  @DisplayName("Postal code is constructed successfully")
  void testPostalCodeIsConstructedSuccessfully() {
    assertDoesNotThrow(
        () -> {
          new NorPostalCode(postalCode, postalName, provinceCode, provinceName, type);
        });
  }

  @Test
  @DisplayName("Postal code with invalid name throws correct exception")
  void testInvalidNameThrowsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new NorPostalCode(postalCode, illegalPostalName, provinceCode, provinceName, type));
  }

  @Test
  @DisplayName("Invalid postal code throws correct exception")
  void testInvalidPostalCodeThrowsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new NorPostalCode(illegalPostalCode, postalName, provinceCode, provinceName, type));
  }

  @Test
  @DisplayName("Postal code is correct after construction")
  void testPostalCodeCorrectAfterConstruction() {
    NorPostalCode code =
        new NorPostalCode(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(postalCode, code.getAsString());
  }

  @Test
  @DisplayName("Postal name is correct after construction")
  void testPostalNameCorrectAfterConstruction() {
    NorPostalCode code =
        new NorPostalCode(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(postalName, code.getName());
  }

  @Test
  @DisplayName("Province code is correct after construction")
  void testProvinceCodeCorrectAfterConstruction() {
    NorPostalCode code =
        new NorPostalCode(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(provinceCode, code.getProvinceCode());
  }

  @Test
  @DisplayName("Province name is correct after construction")
  void testProvinceNameCorrectAfterConstruction() {
    NorPostalCode code =
        new NorPostalCode(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(provinceName, code.getProvinceName());
  }

  @Test
  @DisplayName("Postal code type is correct after construction")
  void testCodeTypeCorrectAfterConstruction() {
    NorPostalCode code =
        new NorPostalCode(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(type, String.valueOf(code.getType().getCode()));
  }

  @Test
  @DisplayName("Postal codes with equal postal numbers are considered equal")
  void testPostalCodesAreEqualWithSamePostalNumber() {
    NorPostalCode a = new NorPostalCode(postalCode, postalName, provinceCode, provinceName, type);
    NorPostalCode b = new NorPostalCode(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(a, b);
  }
}
