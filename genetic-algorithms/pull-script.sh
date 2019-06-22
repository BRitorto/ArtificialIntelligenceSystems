#!/bin/bash
git stash clear
git stash && git pull && git stash pop
