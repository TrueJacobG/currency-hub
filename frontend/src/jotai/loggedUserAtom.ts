import { atom } from "jotai";

const loggedUserAtom = atom({ name: "", firstname: "", surname: "", nick: "", email: "", authCode: "" });

export { loggedUserAtom };
