import { atom } from "jotai";

const loggedUserAtom = atom({ name: "", surname: "", nick: "", email: "", authCode: "" });

export { loggedUserAtom };
