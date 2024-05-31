import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import 'package:get_it/get_it.dart';
import 'package:uuid/uuid.dart';
import 'package:vortasks/models/enums/difficulty.dart';
import 'package:vortasks/models/enums/status.dart';
import 'package:vortasks/models/enums/type.dart';
import 'package:vortasks/models/tasks/task.dart';
import 'package:vortasks/screens/widgets/custom_textfield.dart';
import 'package:vortasks/screens/tasks/widgets/task_button.dart';
import 'package:vortasks/screens/tasks/widgets/skill_dropdown.dart';
import 'package:vortasks/screens/tasks/widgets/switch_field.dart';
import 'package:vortasks/screens/tasks/widgets/task_theme_dropdown.dart';
import 'package:vortasks/screens/tasks/widgets/time_pickers.dart';
import 'package:vortasks/screens/tasks/widgets/toggle_button_field.dart';
import 'package:vortasks/stores/task_form_store.dart';
import 'package:vortasks/stores/skill_store.dart';
import 'package:vortasks/stores/task_store.dart';

import 'date_pickers.dart';

class TaskForm extends StatefulWidget {
  const TaskForm({
    super.key,
    required this.addTaskStore,
    required this.addTaskButton,
  });

  final TaskFormStore addTaskStore;
  final TaskButton addTaskButton;

  @override
  State<TaskForm> createState() => _TaskFormState();
}

class _TaskFormState extends State<TaskForm> {
  final TaskStore _taskStore = GetIt.I<TaskStore>();
  final SkillStore _skillStore = GetIt.I<SkillStore>();

  @override
  void dispose() {
    final TaskFormStore addTaskStore = widget.addTaskStore;
    addTaskStore.clear(); // Limpa os campos do AddTaskStore
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final TaskFormStore addTaskStore = widget.addTaskStore;
    return SingleChildScrollView(
      //color: widget.backgroundColor,
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 26.0, horizontal: 38),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            _buildTitleField(addTaskStore),
            _buildDescriptionField(addTaskStore),
            const SizedBox(height: 20),
            _buildDifficultySection(addTaskStore),
            const SizedBox(height: 20),
            _buildTaskThemeDropdown(addTaskStore),
            const SizedBox(height: 20),
            _buildSkillDropdown(addTaskStore),
            const SizedBox(height: 20),
            _buildAllDaySection(addTaskStore),
            const SizedBox(height: 20),
            _buildEndTimeField(addTaskStore),
            const SizedBox(height: 20),
            _buildStartDateSection(addTaskStore),
            const SizedBox(height: 20),
            _buildEndDateField(addTaskStore),
            const SizedBox(height: 20),
            _buildRepetitionSection(addTaskStore),
            const SizedBox(height: 20),
            _buildReminderSection(addTaskStore),
            const SizedBox(height: 30),
            _buildAddTaskButton(addTaskStore)
          ],
        ),
      ),
    );
  }

  Observer _buildTitleField(TaskFormStore addTaskStore) {
    return Observer(builder: (_) {
      return CustomTextField(
        label: 'Título',
        colorText: Colors.white,
        borderColor: Colors.white,
        cursorColor: Colors.white,
        errorText: addTaskStore.titleError,
        onChanged: (value) {
          addTaskStore.setTitle(value); // Atualiza o título no store
        },
      );
    });
  }

  CustomTextField _buildDescriptionField(TaskFormStore addTaskStore) {
    return CustomTextField(
      label: 'Descrição',
      colorText: Colors.white,
      borderColor: Colors.white,
      onChanged: (value) => addTaskStore.setDescription(value),
    );
  }

  Column _buildDifficultySection(TaskFormStore addTaskStore) {
    return Column(
      children: [
        const Text(
          'Dificuldade',
          style: TextStyle(
              color: Colors.white, fontSize: 16, fontWeight: FontWeight.w600),
        ),
        const SizedBox(height: 10),
        Observer(builder: (_) {
          return ToggleButtonWidget(
            options: const [
              'Fácil',
              'Normal',
              'Difícil',
            ],
            isSelected: [
              // se for igual retorna true deixando a opção selecionada
              addTaskStore.selectedDifficulty == Difficulty.EASY,
              addTaskStore.selectedDifficulty == Difficulty.MEDIUM,
              addTaskStore.selectedDifficulty == Difficulty.HARD,
            ],
            onOptionSelected: (index) {
              switch (index) {
                case 0:
                  addTaskStore.setSelectedDifficulty(Difficulty.EASY);
                  break;
                case 1:
                  addTaskStore.setSelectedDifficulty(Difficulty.MEDIUM);
                  break;
                case 2:
                  addTaskStore.setSelectedDifficulty(Difficulty.HARD);
                  break;
              }
            },
          );
        }),
      ],
    );
  }

  TaskThemeDropdown _buildTaskThemeDropdown(TaskFormStore addTaskStore) {
    return TaskThemeDropdown(
      addTaskStore: addTaskStore,
    );
  }

  Observer _buildSkillDropdown(TaskFormStore addTaskStore) {
    return Observer(
      builder: (_) {
        if (addTaskStore.selectedTaskTheme != null) {
          return SkillDropdown(addTaskStore: addTaskStore);
        } else {
          return Container();
        }
      },
    );
  }

  Observer _buildAllDaySection(TaskFormStore addTaskStore) {
    return Observer(builder: (_) {
      return Column(
        children: [
          buildSwitchDecoration(
            SwitchField(
              title: 'Dia Inteiro',
              value: addTaskStore
                  .allDayEnabled, // necessário pegar o valor do store pois vai reconstruir o widget quando houver mudança
              onChanged: (value) => addTaskStore.switchAllDayEnabled(value),
            ),
          ),
          if (!addTaskStore.allDayEnabled) const SizedBox(height: 10),
        ],
      );
    });
  }

  Observer _buildEndTimeField(TaskFormStore addTaskStore) {
    return Observer(builder: (_) {
      if (!addTaskStore.allDayEnabled) {
        return Column(
          children: [
            TimePickerField(
              label: 'Hora do Término',
              initialTime: addTaskStore.endDate,
              onTimeChanged: (time) => addTaskStore.setEndDate(time),
              enabled: !addTaskStore
                  .allDayEnabled, // Desabilita se 'dia inteiro' estiver ativo
            ),
          ],
        );
      } else {
        return Container();
      }
    });
  }

  Column _buildStartDateSection(TaskFormStore addTaskStore) {
    return Column(
      children: [
        Observer(builder: (_) {
          return SwitchField(
            title: 'Data de Início',
            value: addTaskStore
                .startDateEnabled, // necessário pegar o valor do store pois vai reconstruir o widget quando houver mudança
            onChanged: (value) => addTaskStore.setStartDateEnabled(value),
          );
        }),
        Observer(builder: (_) {
          if (addTaskStore.startDateEnabled) {
            return DatePickerField(
              //label: 'Data de Início:',
              initialDate: addTaskStore.startDate,
              onDateChanged: (date) => addTaskStore.setStartDate(date),
              enabled: addTaskStore
                  .startDateEnabled, // controla se o campo está habilitado
            );
          } else {
            return Container();
          }
        }),
      ],
    );
  }

  Observer _buildEndDateField(TaskFormStore addTaskStore) {
    return Observer(builder: (_) {
      return DatePickerField(
        label: 'Data de Termino:',
        initialDate: addTaskStore.endDate,
        onDateChanged: (date) => addTaskStore.setEndDate(date),
      );
    });
  }

  Column _buildRepetitionSection(TaskFormStore addTaskStore) {
    return Column(
      children: [
        Observer(builder: (_) {
          return SwitchField(
            title: 'Repetição',
            onChanged: (value) => addTaskStore.setRepetitionEnabled(value),
            value: addTaskStore.repetitionEnabled,
          );
        }),
        Observer(builder: (_) {
          if (addTaskStore.repetitionEnabled) {
            return ToggleButtonWidget(
              options: const ['Diária', 'Semanal', 'Mensal'],
              isSelected: [
                // se for igual retorna true deixando a opção selecionada
                addTaskStore.selectedRepetition == 1,
                addTaskStore.selectedRepetition == 7,
                addTaskStore.selectedRepetition == 30
              ],
              onOptionSelected: (index) {
                switch (index) {
                  case 0:
                    addTaskStore.setSelectedRepetition(1);
                    break;
                  case 1:
                    addTaskStore.setSelectedRepetition(7);
                    break;
                  case 2:
                    addTaskStore.setSelectedRepetition(30);
                    break;
                }
              },
            );
          } else {
            return Container();
          }
        }),
      ],
    );
  }

  Column _buildReminderSection(TaskFormStore addTaskStore) {
    return Column(
      children: [
        Observer(builder: (_) {
          return SwitchField(
            title: 'Lembrete',
            onChanged: (value) => addTaskStore.setReminderEnabled(value),
            value: addTaskStore.reminderEnabled,
          );
        }),
        Observer(builder: (_) {
          if (addTaskStore.reminderEnabled) {
            return ToggleButtonWidget(
              options: const ['10min', '1h', '1d'],
              isSelected: [
                // se for igual retorna true deixando a opção selecionada
                addTaskStore.selectedReminder == 10,
                addTaskStore.selectedReminder == 60,
                addTaskStore.selectedReminder == 1440
              ],
              onOptionSelected: (index) {
                switch (index) {
                  case 0:
                    addTaskStore.setSelectedReminder(10);
                    break;
                  case 1:
                    addTaskStore.setSelectedReminder(60);
                    break;
                  case 2:
                    addTaskStore.setSelectedReminder(1440);
                    break;
                }
              },
            );
          } else {
            return Container();
          }
        }),
      ],
    );
  }

  Observer _buildAddTaskButton(TaskFormStore addTaskStore) {
    return Observer(builder: (_) {
      return widget.addTaskButton;
    });
  }

/*
  Observer _buildAddTaskButton(AddTaskStore addTaskStore) {
    return Observer(builder: (_) {
      return AddTaskButton(
        selectedType: addTaskStore.selectedType,
        onPressed: () => _addTask(),
        addTaskStore: addTaskStore,
      );
    });
  }*/

  Container buildSwitchDecoration(SwitchField switcher) {
    return Container(
        padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 22),
        height: 65,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(30),
          border: Border.all(color: Colors.white70),
        ),
        child: switcher);
  }

  void _addTask() {
    final addTaskStore = widget.addTaskStore;
    addTaskStore.setLoading(true);

    if (addTaskStore.isFormValid) {
      final task = _createTaskFromFormData(addTaskStore);
      _taskStore.addTask(task);
      addTaskStore.clear();
      Navigator.pop(context);
    } else {
      addTaskStore.setLoading(false);
      _showValidationError(addTaskStore);
    }
  }

  Task _createTaskFromFormData(TaskFormStore addTaskStore) {
    final selectedSkillsObjects = addTaskStore.selectedSkills
        .map((skillId) =>
            _skillStore.skills.firstWhere((skill) => skill.id == skillId))
        .toList();

    int xp = _calculateXP(addTaskStore.selectedDifficulty);

    Task task = Task(
      id: generateUUID(),
      title: addTaskStore.title!,
      description: addTaskStore.description ?? '',
      type: TaskType.values.byName(addTaskStore.selectedType),
      status: Status.IN_PROGRESS,
      difficulty: addTaskStore.selectedDifficulty,
      theme: addTaskStore.selectedTaskTheme!,
      startDate: addTaskStore.startDate,
      endDate: addTaskStore.endDate,
      repetition: addTaskStore.selectedRepetition ?? 0,
      reminder: addTaskStore.endDate
          .subtract(Duration(minutes: addTaskStore.selectedReminder ?? 0)),
      xp: xp,
      coins: 100,
      skills: selectedSkillsObjects,
      skillIncrease: xp,
      skillDecrease: (xp / 2).round(),
      finish: false,
    );
    print(task.toJson().toString());
    return task;
  }

  void _showValidationError(TaskFormStore addTaskStore) {
    if (!addTaskStore.isTitleValid) {
      _showCustomErrorSnackBar(
          context, 'Por favor, insira um título para a tarefa');
    } else if (addTaskStore.selectedTaskTheme == null) {
      _showCustomErrorSnackBar(
          context, 'Por favor, selecione um tema para a tarefa');
    } else {
      _showCustomErrorSnackBar(
          context, 'Por favor, selecione uma habilidade para a tarefa');
    }
  }

  void _showCustomErrorSnackBar(BuildContext context, String message) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Container(
          padding: const EdgeInsets.all(16.0),
          decoration: BoxDecoration(
            color: Colors.red,
            borderRadius: BorderRadius.circular(10.0),
          ),
          child: Row(
            children: [
              const Icon(
                Icons.error_outline,
                color: Colors.white,
                size: 24,
              ),
              const SizedBox(width: 16),
              Expanded(
                child: Text(
                  message,
                  style: const TextStyle(
                    color: Colors.white,
                    fontSize: 16,
                  ),
                ),
              ),
            ],
          ),
        ),
        behavior: SnackBarBehavior.floating,
        backgroundColor: Colors.transparent, // Fundo transparente
        elevation: 0, // Sem sombra
        duration: const Duration(seconds: 3),
      ),
    );
  }

  int _calculateXP(Difficulty difficulty) {
    switch (difficulty) {
      case Difficulty.EASY:
        return (100 * 0.8).toInt(); // Fácil: 80% da XP normal
      case Difficulty.MEDIUM:
        return 100; // Normal: 100 XP
      case Difficulty.HARD:
        return (100 * 1.5).toInt(); // Difícil: 150% da XP normal
      default:
        return 100; // Valor padrão caso a dificuldade não seja reconhecida
    }
  }
}

String generateUUID() {
  var uuid = const Uuid();
  return uuid.v4();
}
