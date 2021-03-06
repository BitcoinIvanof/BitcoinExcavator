package com.bitcoin.util.serialization.json;

import com.bitcoin.util.BitcoinOptions;
import com.bitcoin.util.Credential;
import com.bitcoin.util.serialization.ObjectDeserializer;
import com.bitcoin.util.serialization.ObjectSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ObjectJsonSerializationTests {
    private final BitcoinOptions options;

    private final ObjectSerializer<BitcoinOptions> serializer = new ObjectJsonSerializer<>();

    private final ObjectDeserializer<BitcoinOptions> deserializer = new ObjectJsonDeserializer<>();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        { new BitcoinOptions() }
                };
        return Arrays.asList(data);
    }

    public ObjectJsonSerializationTests(BitcoinOptions options) {
        options.addCredential(new Credential("login", "password", "host", "protocol", "path", 1));
        this.options = options;
    }

    @Test
    public void isReversibleOperation() throws IOException {
        String serialized = serializer.serialize(options);
        BitcoinOptions deserialized = deserializer.deserialize(serialized);
        assertThat(deserialized, is(equalTo(options)));
    }
}
