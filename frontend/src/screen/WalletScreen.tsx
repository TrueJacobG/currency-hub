import AsyncStorage from "@react-native-async-storage/async-storage";
import { Link } from "react-router-native";
import { View, Text, StyleSheet } from "react-native";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { WalletService } from "../services/WalletService";
import { User } from "../type/User";
import { useAtom } from "jotai";
import { useEffect } from "react";

const WalletScreen = () => {
  const walletService = new WalletService();

  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);

  useEffect(() => {
    walletService.getUserWallet().catch((error) => {
      console.error(error);
    });
  }, []);

  const getData = async () => {
    let data = await AsyncStorage.getItem("token");
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <View>
      <View>
        <Text style={styles.textintro}>Welcome to your wallet!</Text>
        <View>
          <Text>Email: {loggedUser.email}</Text>
        </View>
      </View>
      <View>
        <Text>WALLET HERE</Text>
      </View>
      <View style={styles.bottomView}>
        <MenuComponent />
      </View>
    </View>
  );
};

export default WalletScreen;

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
