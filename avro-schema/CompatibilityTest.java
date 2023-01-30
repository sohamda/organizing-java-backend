
public class CompatibilityTest {
    @Test
    public void testBackwardsCompatibility() throws IOException {
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("test-resources/AvroSchema.avsc")) {
            assert is != null;

            var provider = new AvroSchemaProvider();
            var newSchema = provider.parseSchema(Event.SCHEMA$.toString(false), Collections.emptyList()).orElseThrow();
            var oldSchema = provider.parseSchema(new String(is.readAllBytes()), Collections.emptyList()).orElseThrow();
            var result = CompatibilityChecker.FULL_TRANSITIVE_CHECKER.isCompatible(newSchema, List.of(oldSchema));

            assertTrue(result.isEmpty(), () -> {
                var builder = new StringBuilder();
                builder.append("Incompatibilities were found in schema:\n");
                result.forEach(builder::append);
                return builder.toString();
            });
        }
    }

}

