import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.tobiasth.idatt2001.postalregistry.model.PostalNumber;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Norwegian Postal Number Tests")
class PostalNumberTest {
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
          new PostalNumber(postalCode, postalName, provinceCode, provinceName, type);
        });
  }

  @Test
  @DisplayName("Postal code with invalid name throws correct exception")
  void testInvalidNameThrowsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new PostalNumber(postalCode, illegalPostalName, provinceCode, provinceName, type));
  }

  @Test
  @DisplayName("Invalid postal code throws correct exception")
  void testInvalidPostalCodeThrowsException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new PostalNumber(illegalPostalCode, postalName, provinceCode, provinceName, type));
  }

  @Test
  @DisplayName("Postal code is correct after construction")
  void testPostalCodeCorrectAfterConstruction() {
    PostalNumber code = new PostalNumber(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(postalCode, code.getCode());
  }

  @Test
  @DisplayName("Postal name is correct after construction")
  void testPostalNameCorrectAfterConstruction() {
    PostalNumber code = new PostalNumber(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(postalName, code.getLocationName());
  }

  @Test
  @DisplayName("Province code is correct after construction")
  void testProvinceCodeCorrectAfterConstruction() {
    PostalNumber code = new PostalNumber(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(provinceCode, code.getProvinceCode());
  }

  @Test
  @DisplayName("Province name is correct after construction")
  void testProvinceNameCorrectAfterConstruction() {
    PostalNumber code = new PostalNumber(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(provinceName, code.getProvinceName());
  }

  @Test
  @DisplayName("Postal code type is correct after construction")
  void testCodeTypeCorrectAfterConstruction() {
    PostalNumber code = new PostalNumber(postalCode, postalName, provinceCode, provinceName, type);
    PostalNumber.Type expectedType =
        Arrays.stream(PostalNumber.Type.values())
            .filter(t -> t.getCode() == type.charAt(0))
            .findAny()
            .orElse(PostalNumber.Type.UNKNOWN);

    assertEquals(expectedType.getDescription(), String.valueOf(code.getTypeDescription()));
  }

  @Test
  @DisplayName("Postal codes with equal postal numbers are considered equal")
  void testPostalCodesAreEqualWithSamePostalNumber() {
    PostalNumber a = new PostalNumber(postalCode, postalName, provinceCode, provinceName, type);
    PostalNumber b = new PostalNumber(postalCode, postalName, provinceCode, provinceName, type);

    assertEquals(a, b);
  }
}
