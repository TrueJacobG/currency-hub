import { StyleSheet, View, Button, Pressable, Text } from "react-native";
import LoginComponent from "../components/sign/login/LoginComponent";
import SigninComponents from "../components/sign/login/SigninComponents";
import { NavigationContainer } from '@react-navigation/native';
import { NativeStackScreenProps, createNativeStackNavigator } from '@react-navigation/native-stack';
import { ScreenNaviagtion } from "../type/ScreenNavigation";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Log">;

const LogScreen = ({ navigation }: Props) => {
  return (
    <View>
      <LoginComponent />
      <Pressable style={() => [styles.button]} onPress={() => navigation.navigate('Log')}>
      <Text style={[styles.text]}>Login</Text>
      </Pressable>
    </View>
  );
};

export default LogScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  text: {
    fontSize: 30,
    textAlign: 'center',
    marginTop: 30
  },
  button: {
    borderWidth: 1,
    backgroundColor: 'pink',
  },
});
