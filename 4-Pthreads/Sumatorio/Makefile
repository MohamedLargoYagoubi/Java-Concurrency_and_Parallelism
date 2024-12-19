CC = gcc

CFLAGS = -Wall -Wextra -std=c99
LDFLAGS = -pthread

TARGET = SumatorioMod

SRCS = sumatorio_mod.c

OBJS = $(SRCS:.c=.o)

ifeq ($(OS),Windows_NT)
    RM = del
    EXE = .exe
    LDFLAGS := 
else
    RM = rm -f
    EXE = 
endif

all: $(TARGET)$(EXE)

$(TARGET)$(EXE): $(OBJS)
	$(CC) $(OBJS) -o $@ $(LDFLAGS)

%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

clean:
	$(RM) $(OBJS) $(TARGET)$(EXE)

.PHONY: all clean


debug: CFLAGS += -g -O0
debug: all

release: CFLAGS += -O3
release: all

windows:
	mingw32-gcc $(SRCS) -o $(TARGET).exe

unix:
	gcc $(SRCS) -o $(TARGET) -pthread

help:
	@echo "Makefile for Concurrent Summation Project"
	@echo "Targets:"
	@echo "  all      - Build the default executable"
	@echo "  debug    - Build with debugging symbols"
	@echo "  release  - Build with optimizations"
	@echo "  clean    - Remove compiled files"
	@echo "  windows  - Build specifically for Windows"
	@echo "  unix     - Build specifically for Unix-like systems"