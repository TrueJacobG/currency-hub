import { StyleSheet, View, Button, Pressable, Text } from "react-native";
import LoginComponent from "../components/sign/login/LoginComponent";
import SigninComponents from "../components/sign/login/SigninComponents";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Sign">;

const SignScreen = ({ navigation }: Props) => {
  return (
    <View>
      <SigninComponents />
      <Pressable onPress={() => navigation.navigate("Sign")}>
        <Text style={[styles.text]}>Login</Text>
      </Pressable>
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
  text: {
    color: "black",
  },
});
