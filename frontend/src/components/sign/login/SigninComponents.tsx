import { useState } from "react";
import { StyleSheet, TextInput, View, Button, Text } from "react-native";

const SigninComponents = () => {
  const [text, onChangeText] = useState("Useless Text");

  return (
    <View>
      <TextInput style={styles.container} onChangeText={onChangeText} value={"email"} />
      <TextInput style={styles.container} onChangeText={onChangeText} value={"password"} />
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

export default SigninComponents;