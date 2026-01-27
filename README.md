


# 🧠 Visão geral (antes do detalhe)

O que você construiu é isso:

```bash
Button (UI)
  ↓ clique
ViewModel.changeMessage()
  ↓ altera
MutableStateFlow
  ↓ emite novo valor
collectAsState()
  ↓ avisa o Compose
Recomposição
  ↓
Text atualizado
```
--- 

```kotlin
val state = viewModel.message.collectAsState()
val message = state.value
======
val message by viewModel.message.collectAsState()
```


# 🧩 Regra de ouro que você acabou de aprender

> **TextField não é dono do texto.
> ViewModel é.**

Se você entendeu isso, **entendeu Compose + MVVM**.
