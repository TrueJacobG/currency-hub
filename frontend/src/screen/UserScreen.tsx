import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useAtom } from "jotai";
import React, { useEffect } from "react";
import { StyleSheet, Text, View } from "react-native";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { UserService } from "../services/UserService";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { User } from "../type/User";

type Props = NativeStackScreenProps<ScreenNaviagtion, "User">;

const UserScreen = ({ navigation }: Props) => {
  const userService = new UserService();

  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);

  useEffect(() => {}, []);

  return (
    <View>
      <View>
        <Text style={styles.textintro}>Welcome to your profile!</Text>
        <View>
          <Text>Email: {loggedUser.email}</Text>
          <Text>Password: {loggedUser.authCode}</Text>
        </View>
      </View>
      <View style={styles.bottomView}>
        <MenuComponent />
      </View>
    </View>
  );
};

export default UserScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  textintro: {
    textAlign: "center",
    fontSize: 40,
    marginTop: 20,
  },
  text: {
    textAlign: "center",
    fontSize: 10,
    marginBottom: 40,
  },
  bottomView: {
    alignItems: "center",
    marginTop: "auto",
  },
});
