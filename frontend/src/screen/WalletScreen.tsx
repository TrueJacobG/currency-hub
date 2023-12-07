import AsyncStorage from "@react-native-async-storage/async-storage";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useAtom } from "jotai";
import React, { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { WalletService } from "../services/WalletService";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { User } from "../type/User";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Wallet">;

const WalletScreen = ({ navigation }: Props) => {
    const walletService = new WalletService();

    const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);

    useEffect(() => {
        walletService
            .getUserWallet()
            .catch((error) => {
                console.error(error);
            });
    }, []);

    const getData = async () => {
        let data = await AsyncStorage.getItem("token");
        console.log("WORKING WALLET SCREEN");
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
