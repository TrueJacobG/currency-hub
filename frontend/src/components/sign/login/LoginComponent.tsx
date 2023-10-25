import { useState } from "react";
import { StyleSheet, TextInput, View } from "react-native";

const LoginComponent = () => {
  const [text, onChangeText] = useState("Useless Text");

  return (
    <View>
      <TextInput style={styles.container} onChangeText={onChangeText} value={"field"} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    textAlign: "center",
    textAlignVertical: "center",
    fontSize: 18,
    width: 200,
    backgroundColor: "yellow",
  },
});

export default LoginComponent;
