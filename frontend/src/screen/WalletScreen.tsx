import AsyncStorage from "@react-native-async-storage/async-storage";
import {
  View,
  Text,
  StyleSheet,
  Button,
  TextInput,
  FlatList,
} from "react-native";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { WalletService } from "../services/WalletService";
import { Wallet } from "../type/Wallet";
import { User } from "../type/User";
import { useAtom } from "jotai";
import { useEffect, useState } from "react";

const WalletScreen = () => {
  const walletService = new WalletService();

  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);
  const [wallet, setWallet] = useState(new Map());
  const [inputValue, setInputValue] = useState("");

  useEffect(() => {
    getData();

    walletService
      .getUserWallet()
      .then((data) => setWallet(data))
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const getData = async () => {
    let data = await AsyncStorage.getItem("token");
  };

  //TODO::dodac jakiekolwiek wyswietlanie
  //TODO::mam mape, to przyjmuje Array jakis, nie wiem jak zmienic zeby dane byly dobrze
  //TODO:: dodac ekran wymiany
  return (
    <View>
      <View>
        <Text style={styles.textintro}>Welcome to your wallet!</Text>
        <View>
          <Text>Email: {loggedUser.email}</Text>
        </View>
      </View>
      <View>
        <TextInput
          placeholder="Input cash here"
          onChangeText={(text) => setInputValue(text)}
        ></TextInput>
        <Button
          title="AddCash"
          onPress={() => walletService.addCash(parseFloat(inputValue))}
        ></Button>

        <FlatList
          data={wallet}
          renderItem={({ item }) => <Text>asd</Text>}
          keyExtractor={(item) => item}
        />
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
