import React, { useState } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import SigninComponent from "../components/auth/SigninComponent";
import { AuthorizationService } from "../services/AuthorizationService";
import { User } from "../type/User";
import { Routes, Route, useNavigate } from "react-router-native";

const SignScreen = () => {
  const authService = new AuthorizationService();
  const navigate = useNavigate();

  const [user, setUser] = useState<User>({
    name: "",
    firstname: "",
    surname: "",
    nick: "",
    email: "",
    authCode: "",
  });

  const onSignInPress = async () => {
    if (typeof user === "undefined") {
      console.error("User is undefined!");
      return;
    }

    const result = await authService.registerUser(user);

    if (result.message === "ok" && result.status === "ACCEPTED") {
      setUser({
        name: "",
        firstname: "",
        surname: "",
        nick: "",
        email: "",
        authCode: "",
      });
      navigate("/home");
    }
  };

  return (
    <View>
      <SigninComponent user={user} setUser={setUser} />
      <Pressable style={styles.button} onPress={onSignInPress}>
        <Text style={[styles.textbut]}>Signin</Text>
      </Pressable>
      <Text style={styles.textintro}>
        If you already have an account please login:
      </Text>
      <Pressable
        style={() => [styles.button]}
        onPress={() => navigate("/login")}
      >
        <Text style={[styles.textbut]}>Login</Text>
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
  button: {
    borderWidth: 1,
    backgroundColor: "pink",
  },
  textbut: {
    textAlign: "center",
    fontSize: 18,
    marginBottom: 10,
    marginTop: 10,
  },
  textintro: {
    fontSize: 15,
    textAlign: "center",
    paddingBottom: 10,
    paddingTop: 10,
  },
});