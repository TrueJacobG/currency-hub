import { StyleSheet, View } from "react-native";
import LoginComponent from "../components/sign/login/LoginComponent";

const SignScreen = () => {
  return (
    <View>
      <LoginComponent />
    </View>
  );
};

export default SignScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
