import React from "react";
import { Link } from "react-router-native";
import { View, Text, StyleSheet, Button } from "react-native";
import { Routes, Route, useNavigate } from "react-router-native";

const MenuComponent = () => {
  const navigate = useNavigate();
  return (
    <View style={styles.container}>
      <Button title="All" onPress={() => navigate("/home")}></Button>
      <Button title="Fav" onPress={() => navigate("/favourite")}></Button>
      <Button title="Wallet" onPress={() => navigate("/wallet")}></Button>
      <Button title="Alerts" onPress={() => navigate("/alert")}></Button>
      <Button title="Profile" onPress={() => navigate("/user")}></Button>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
  },
});

export default MenuComponent;