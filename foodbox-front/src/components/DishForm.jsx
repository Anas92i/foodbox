import { useState } from "react";
import api from "../api/api";

export default function DishForm({ onCreated }) {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");

  const submit = async (e) => {
    e.preventDefault();
    await api.post("/dishes", { name, description });
    setName(""); setDescription("");
    onCreated();
  };

  return (
    <form onSubmit={submit}>
      <input placeholder="name" value={name} onChange={e=>setName(e.target.value)} />
      <input placeholder="description" value={description} onChange={e=>setDescription(e.target.value)} />
      <button>Create</button>
    </form>
  );
}
