import { useEffect, useState } from "react";
import api from "../api/api";
import DishForm from "../components/DishForm";

export default function Dishes() {
  const [dishes, setDishes] = useState([]);

  const load = async () => {
    const res = await api.get("/dishes");
    setDishes(res.data);
  };

  useEffect(() => { load(); }, []);

  return (
    <div>
      <h1>Dishes</h1>
      <DishForm onCreated={load} />
      <ul>
        {dishes.map(d => (
          <li key={d.id}>{d.name}</li>
        ))}
      </ul>
    </div>
  );
}
