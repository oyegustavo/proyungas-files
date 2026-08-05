package ar.org.proyungas.exception;



public enum ErrorCode {
  INTERNAL_ERROR(100, "An internal error ocurred", "INTERNAL_ERROR"),
  BAD_REQUEST_ERROR(101, "Bad request", "BAD_REQUEST_ERROR"),
  DATABASE_ERROR(102, "Database Error", "DATABASE_ERROR"),
  REST_CLIENT_ERROR(103, "Unexpected rest client error", "REST_CLIENT_ERROR"),
  NOT_FOUND_ERROR(104, "Not Found Error", "NOT_FOUND_ERROR"),
  EXTERNAL_SERVICE_ERROR(105, "External service error", "EXTERNAL_SERVICE_ERROR"),
  INVALID_STATUS_ERROR(106, "Invalid Status Error", "INVALID_STATUS_ERROR"),
  FILE_SIZE_ERROR(107, "File Size Error", "FILE_SIZE_ERROR"),
  INVALID_EXTENSION_ERROR(108, "Invalid Extension Error", "INVALID_EXTENSION_ERROR"),
  MISSING_SHAPEFILE_ERROR(108, "Missing Shapefile Error", "MISSING_SHAPEFILE_ERROR"),
  INVALID_ZIP_ERROR(108, "Invalid Zip Error", "INVALID_ZIP_ERROR"),
  INVALID_KMZ_ERROR(108, "Invalid KMZ Error", "INVALID_KMZ_ERROR"),
  INVALID_GEO_JSON_ERROR(108, "Invalid Geo Json Error", "INVALID_GEO_JSON_ERROR");


  private final int value;
  private final String reason;
  private final String code;

  ErrorCode(int value, String reason, String code) {
    this.value = value;
    this.reason = reason;
    this.code = code;
  }

  public int value() {
    return this.value;
  }

  public String getReason() {
    return this.reason;
  }

  public String getCode() {
    return this.code;
  }
}
