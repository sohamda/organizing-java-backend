
public class SchemaGenerator {

    public void generateSchema() {
        Schema clientIdentifier = SchemaBuilder.record("User")
                .namespace("com.company.avro")
                .fields().requiredString("firstname").requiredString("lastname")
                .endRecord();
    }
}