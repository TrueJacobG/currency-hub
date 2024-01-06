import AsyncStorage from "@react-native-async-storage/async-storage";
import { useAtom } from "jotai";
import { useEffect, useState } from "react";
import {
  Button,
  FlatList,
  StyleSheet,
  Text,
  TextInput,
  View,
} from "react-native";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { WalletService } from "../services/WalletService";
import { CurrencyService } from "../services/CurrencyService";
import { User } from "../type/User";
import { Dropdown } from "react-native-element-dropdown";
import { Currency } from "../type/Currency";

const WalletScreen = () => {
  const walletService = new WalletService();
  const currencyService = new CurrencyService();

  const [userWallet, setUserWallet] = useState<number>(-1);
  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);
  const [wallet, setWallet] = useState([]);
  const [currencies, setCurrencies] = useState([]);
  const [smallCurrencies, setSmallCurrencies] = useState([]);
  const [inputValue, setInputValue] = useState("");
  const [walletValue, setWalletValue] = useState(String);
  const [currencyValue, setCurrencyValue] = useState(String);
  const [exchangeValue, setExchangeValue] = useState(Number);
  const [isWalletFocus, setIsWalletFocus] = useState(false);
  const [isCurrencyFocus, setIsCurrencyFocus] = useState(false);

  const addToUserWallet = () => {
    walletService.addCash(parseFloat(inputValue)).then((value) => {
      setUserWallet(value);
    });
  };

  const exchange = () => {
    walletService.exchange(
      walletValue.currencyCode,
      currencyValue.currencyCode,
      exchangeValue
    );

    console.log("EXCHANGE");
  };

  useEffect(() => {
    getData();

    walletService
      .getUserWallet()
      .then((data) => {
        setUserWallet(data[0].value);
        setWallet(data);
      })
      .catch((error) => {
        console.error(error);
      });
    currencyService
      .getAllCurrencies()
      .then((data) => {
        setCurrencies(data.list);
        setSmallCurrencies(data.list.slice(0, 50));
      })
      .catch((error) => {
        console.error(error);
      });
    console.log("finished fetching data");
  }, []);

  const getData = async () => {
    let data = await AsyncStorage.getItem("token");
  };

  //TODO:: dodac ekran wymiany
  return (
    <View>
      <View>
        <Text style={styles.textintro}>Welcome to your wallet!</Text>
      </View>
      <View>
        <Text>{userWallet === -1 ? "Not fetched" : userWallet}</Text>
      </View>
      <View>
        <TextInput
          placeholder="Input cash here"
          keyboardType="numeric"
          onChangeText={(text) => setInputValue(text)}
        />
        <Button title="AddCash" onPress={() => addToUserWallet()}></Button>
        <View style={styles.wallet}>
          <FlatList
            data={wallet}
            renderItem={({ item }) => (
              <Text>
                {item.currencyCode} {item.value}
              </Text>
            )}
          />
        </View>
        <View style={styles.container2}>
          <Dropdown
            style={[styles.dropdown, isWalletFocus && { borderColor: "blue" }]}
            placeholderStyle={styles.placeholderStyle}
            selectedTextStyle={styles.selectedTextStyle}
            inputSearchStyle={styles.inputSearchStyle}
            data={wallet}
            search
            maxHeight={300}
            labelField="currencyCode"
            valueField="value"
            placeholder={!isWalletFocus ? "Select item" : "..."}
            searchPlaceholder="Search..."
            value={walletValue}
            onFocus={() => setIsWalletFocus(true)}
            onBlur={() => setIsWalletFocus(false)}
            onChange={(item) => {
              setWalletValue(item);
              setIsWalletFocus(false);
            }}
          />
        </View>
        <TextInput
          placeholder="Input cash here"
          keyboardType="numeric"
          onChangeText={(text) => setExchangeValue(Number(text))}
        />
        <View style={styles.container2}>
          <Dropdown
            style={[
              styles.dropdown,
              isCurrencyFocus && { borderColor: "blue" },
            ]}
            placeholderStyle={styles.placeholderStyle}
            selectedTextStyle={styles.selectedTextStyle}
            inputSearchStyle={styles.inputSearchStyle}
            data={smallCurrencies}
            search
            maxHeight={300}
            labelField="currencyCode"
            valueField="mid"
            placeholder={!isCurrencyFocus ? "Select item" : "..."}
            searchPlaceholder="Search..."
            value={walletValue}
            onFocus={() => setIsCurrencyFocus(true)}
            onBlur={() => setIsCurrencyFocus(false)}
            onChange={(item) => {
              setCurrencyValue(item);
              setIsCurrencyFocus(false);
            }}
          />
        </View>
        <View>
          <Button onPress={exchange} title="Exchange"></Button>
        </View>
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
  wallet: {},
  container2: {
    backgroundColor: "white",
    padding: 16,
  },
  dropdown: {
    height: 50,
    borderColor: "gray",
    borderWidth: 0.5,
    borderRadius: 8,
    paddingHorizontal: 8,
  },
  icon: {
    marginRight: 5,
  },
  label: {
    position: "absolute",
    backgroundColor: "white",
    left: 22,
    top: 8,
    zIndex: 999,
    paddingHorizontal: 8,
    fontSize: 14,
  },
  placeholderStyle: {
    fontSize: 16,
  },
  selectedTextStyle: {
    fontSize: 16,
  },
  inputSearchStyle: {
    height: 40,
    fontSize: 16,
  },
});
