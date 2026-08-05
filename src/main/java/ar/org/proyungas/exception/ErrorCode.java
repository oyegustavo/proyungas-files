package ar.org.proyungas.exception;



public enum ErrorCode {
  INTERNAL_ERROR(100, "An internal error ocurred", "INTERNAL_ERROR"),
  BAD_REQUEST_ERROR(101, "Bad request", "BAD_REQUEST_ERROR"),
  DATABASE_ERROR(102, "Database Error", "DATABASE_ERROR"),
  REST_CLIENT_ERROR(103, "Unexpected rest client error", "REST_CLIENT_ERROR"),
  NOT_FOUND_ERROR(104, "Not Found Error", "NOT_FOUND_ERROR"),
  EXTERNAL_SERVICE_ERROR(105, "External service error", "EXTERNAL_SERVICE_ERROR"),
  PLAN_TYPE_NOT_FOUND(106, "Plan Type Not Found Error", "PLAN_TYPE_NOT_FOUND_ERROR"),
  FORBIDDEN_ACTION_APPLICANT(107, "Forbidden Action Applicant", "FORBIDDEN_ACTION_APPLICANT"),
  CRIME_SCENE_NOT_FOUND(107, "Crime Scene Not Found Error", "CRIME_SCENE_NOT_FOUND_ERROR"),
  CRIME_TYPE_NOT_FOUND(108, "Crime Type Not Found Error", "CRIME_TYPE_NOT_FOUND_ERROR"),
  AUDIENCE_NOT_FOUND(109, "Audience Not Found Error", "AUDIENCE_NOT_FOUND_ERROR"),
  AUDIENCE_BAD_REQUEST(110, "Audience Bad Request Error", "AUDIENCE_BAD_REQUEST_ERROR"),
  AUDIENCE_HISTORY_NOT_FOUND(111, "Audience History Not Found Error", "AUDIENCE_HISTORY_NOT_FOUND_ERROR"),
  AUDIT_REQUIRED_FIELD_ERROR(112, "Audit required field Error", "AUDIT_REQUIRED_FIELD_ERROR"),
  RESULT_NOT_FOUND(113, "Result Not Found Error", "RESULT_NOT_FOUND"),
  ACCUSED_NOT_FOUND(114, "Accused Not Found Error", "ACCUSED_NOT_FOUND"),
  MODALITY_NOT_FOUND(115, "Modality Not Found Error", "MODALITY_NOT_FOUND"),
  INVALID_ACTION_APPLICANT_ERROR(116, "Invalid Action Applicant Error", "INVALID_ACTION_APPLICANT_ERROR"),
  INVALID_MODALITY_ERROR(117, "Invalid Modality Error", "INVALID_MODALITY_ERROR"),
  INTERMEDIATE_NOT_FOUND(118, "Intermediate Not Found Error", "INTERMEDIATE_NOT_FOUND_ERROR"),
  INTERMEDIATE_BAD_REQUEST(119, "Intermediate Bad Request Error", "INTERMEDIATE_BAD_REQUEST_ERROR"),
  USER_NOT_FOUND(120, "User Not Found Error", "USER_NOT_FOUND_ERROR"),
  USER_BAD_REQUEST(121, "User Bad Request Error", "USER_BAD_REQUEST_ERROR"),
  INVALID_DATE_RANGE(122, "Invalid Date Range", "INVALID_DATE_RANGE"),
  AUDIT_LOG_BAD_REQUEST(123, "Audit Log Bad Request Error", "AUDIT_LOG_BAD_REQUEST"),
  CANCELATION_MOTIVE_BAD_REQUEST(124, "Cancelation Motive Bad Request Error", "CANCELATION_MOTIVE_BAD_REQUEST"),
  CANCELATION_MOTIVE_NOT_FOUND(125, "Cancelation Motive Not Found Error", "CANCELATION_MOTIVE_NOT_FOUND"),
  INVALID_ACTION_ERROR(126,"Invalid Action Error","INVALID_ACTION_ERROR"),
  INVALID_VECTORIAL_LAYER_ERROR(127,"Invalid Vectorial Layer Error","INVALID_VECTORIAL_LAYER_ERROR"),
  INVALID_STATUS_PROGRESSION_ERROR(128,"Invalid Status Progression Error","INVALID_STATUS_PROGRESSION_ERROR"),
  INVALID_CRIME_ERROR(129,"Invalid Crime Error","INVALID_CRIME_ERROR"),
  ACTION_NOT_FOUND(130, "Action Not Found Error", "ACTION_NOT_FOUND"),
  INVALID_FILTER(131, "Invalid search filter", "INVALID_FILTER"),
  REPEATED_ACTION_NUMBER_ERROR(132, "Repeated Action Number", "REPEATED_ACTION_NUMBER_ERROR"),
  INVALID_FILTER_TYPE_ERROR(133, "Filter type not valid", "INVALID_FILTER_TYPE_ERROR"),
  LAYER_TEMPLATE_NOT_FOUND_ERROR(134,"Layer Template Not Found Error","LAYER_TEMPLATE_NOT_FOUND_ERROR"),
  CASE_FILE_CRIME_SCENE_BAD_REQUEST_ERROR(135, "Case File Crime Scene Bad Request Error", "CASE_FILE_CRIME_SCENE_BAD_REQUEST_ERROR"),
  AUDIENCE_FORBIDDEN(136, "Audience Forbidden", "AUDIENCE_FORBIDDEN"),
  VECTORIAL_LAYER_NOT_FOUND(137, "Vectorial Layer Not Found", "VECTORIAL_LAYER_NOT_FOUND"),
  ACCUSED_LAWYER_BUSY(138, "Accused Lawyer Busy", "ACCUSED_LAWYER_BUSY"),
  PERSON_NOT_FOUND(139, "Person Not Found", "PERSON_NOT_FOUND"),
  VICTIM_NOT_FOUND(140, "Victim Not Found Error", "VICTIM_NOT_FOUND"),
  MALFORMED_FILTER_EXCEPTION(141, "Malformed Filter Error", "MALFORMED_FILTER_EXCEPTION"),
  ACCUSED_LAWYER_NOT_FOUND(142, "Accused Lawyer Not Found Error", "ACCUSED_LAWYER_NOT_FOUND"),
  ACCUSED_BAD_REQUEST_ERROR(143, "Accused Bad Request Error", "ACCUSED_BAD_REQUEST_ERROR"),
  AUDIENCE_TYPE_NOT_REQUIRED_DEFENSE(144, "Audience Type Not Required Defense Error", "AUDIENCE_TYPE_NOT_REQUIRED_DEFENSE"),
  ACCUSED_CRIME_VICTIM_DUPLICATED(145, "Victim already associated to accused crime", "ACCUSED_CRIME_VICTIM_DUPLICATED"),
  VICTIM_LIST_EMPTY(146, "No victim identified to associate with accused crime", "VICTIM_LIST_EMPTY"),
  JSON_SERIALIZER_ERROR(147, "Json Serializer Error", "JSON_SERIALIZER_ERROR"),
  AGENCY_NOT_FOUND(148,"Agency Not Found", "AGENCY_NOT_FOUND"),
  ACCUSED_CRIME_BAD_REQUEST_ERROR(149, "Accused Crime Bad Request Error", "ACCUSED_CRIME_BAD_REQUEST_ERROR"),
  CASE_FILE_CRIME_SCENE_PRIMARY_CONFLICT(150, "A primary crime scene already exists for this case file", "CASE_FILE_CRIME_SCENE_PRIMARY_CONFLICT"),
  CASE_FILE_CRIME_SCENE_PRIMARY_DELETE_CONFLICT(151, "A primary record cannot be deleted", "CASE_FILE_CRIME_SCENE_PRIMARY_DELETE_CONFLICT");


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
