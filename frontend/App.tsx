import React from "react";
import { NativeRouter as Router, Route, Routes } from "react-router-native";
import SigninScreen from "./src/screen/SigninScreen";
import LoginScreen from "./src/screen/LoginScreen";
import BaseScreen from "./src/screen/BaseScreen";
import FavouriteScreen from "./src/screen/FavouriteScreen";
import WalletScreen from "./src/screen/WalletScreen";
import AlertScreen from "./src/screen/AlertScreen";
import UserScreen from "./src/screen/UserScreen";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SigninScreen />} />
        <Route path="login" element={<LoginScreen />} />
        <Route path="home" element={<BaseScreen />} />
        <Route path="favourite" element={<FavouriteScreen />} />
        <Route path="wallet" element={<WalletScreen />} />
        <Route path="alert" element={<AlertScreen />} />
        <Route path="user" element={<UserScreen />} />
      </Routes>
    </Router>
  );
};

export default App;