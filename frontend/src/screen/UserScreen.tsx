import { Link } from "react-router-native";
import { View, Text, StyleSheet } from "react-native";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { UserService } from "../services/UserService";
import { User } from "../type/User";
import { useAtom } from "jotai";
import { useEffect } from "react";

const UserScreen = () => {
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
